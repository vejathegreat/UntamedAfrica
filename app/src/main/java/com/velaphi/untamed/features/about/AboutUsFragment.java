package com.velaphi.untamed.features.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.injection.UntamedFactory;

import java.util.Objects;

public class AboutUsFragment extends Fragment {

    private AboutUsViewModel aboutUsViewModel;
    private ProgressBar progressBar;
    private LinearLayout dataErrorStateLinearLayout;
    private LinearLayout networkErrorStateLinearLayout;
    private AboutAdapter aboutAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generic_with_recyclerview, container, false);
        setupToolbar(view);
        setupRecyclerview(view);
        setupViewModel();
        setupView(view);
        aboutUsViewModel.retrieveAboutUsListFromFirebase();
        return view;
    }

    private void setupView(View view) {
        dataErrorStateLinearLayout = view.findViewById(R.id.data_error_state_layout);
        networkErrorStateLinearLayout = view.findViewById(R.id.network_error_state_layout);
        dataErrorStateLinearLayout.setVisibility(View.GONE);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Button refreshButton = view.findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(v -> Objects.requireNonNull(getActivity())
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new AboutUsFragment())
                .commit());
    }

    private void setupRecyclerview(View view) {
        RecyclerView aboutUsRecyclerView = view.findViewById(R.id.recyclerview_generic);
        aboutAdapter = new AboutAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        aboutUsRecyclerView.setLayoutManager(layoutManager);
        aboutUsRecyclerView.setNestedScrollingEnabled(false);
        aboutUsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        aboutUsRecyclerView.setAdapter(aboutAdapter);
    }

    private void setupViewModel() {
        UntamedAfricaApp application = (UntamedAfricaApp) getActivity().getApplication();
        aboutUsViewModel = ViewModelProviders.of(this, new UntamedFactory(application))
                .get(AboutUsViewModel.class);
        observeAboutUsList();
        observeExceptionMessage();
    }

    private void observeAboutUsList() {

        aboutUsViewModel.getAboutUsMutableLiveData().observe(this, aboutUSList -> {
            progressBar.setVisibility(View.GONE);

            if (aboutUSList != null) {
                if (aboutUSList.isEmpty()) {
                    dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
                    networkErrorStateLinearLayout.setVisibility(View.GONE);
                } else {
                    aboutAdapter.setItems(aboutUSList);
                }
            } else {
                dataErrorStateLinearLayout.setVisibility(View.GONE);
                networkErrorStateLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void observeExceptionMessage() {
        aboutUsViewModel.getExceptionMessage().observe(this, e -> {
            progressBar.setVisibility(View.GONE);
            dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
            networkErrorStateLinearLayout.setVisibility(View.GONE);
        });
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu_about);
        toolbar.setNavigationIcon(R.drawable.ic_nav_open);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(GravityCompat.START);

        });
    }
}
