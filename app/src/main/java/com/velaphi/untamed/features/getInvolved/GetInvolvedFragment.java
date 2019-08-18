package com.velaphi.untamed.features.getInvolved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.injection.UntamedFactory;

import java.util.Objects;

public class GetInvolvedFragment extends Fragment {

    private GetInvolvedViewModel getInvolvedViewModel;
    private ProgressBar progressBar;
    private LinearLayout dataErrorStateLinearLayout;
    private LinearLayout networkErrorStateLinearLayout;
    private FoundationsAdapter foundationsAdapter;
    private TextView headerTextView;

    public GetInvolvedFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_get_involved, container, false);
        setupToolbar(view);
        setupViewModel();
        setupRecyclerView(view);
        setupView(view);
        getInvolvedViewModel.retrieveListOfLicencesFromFirebase();
        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView getInvolvedRecyclerView = view.findViewById(R.id.recyclerview_generic);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        foundationsAdapter = new FoundationsAdapter();
        getInvolvedRecyclerView.setLayoutManager(layoutManager);
        getInvolvedRecyclerView.setNestedScrollingEnabled(false);
        getInvolvedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getInvolvedRecyclerView.setAdapter(foundationsAdapter);

    }

    private void setupView(View view) {
        headerTextView = view.findViewById(R.id.header);
        dataErrorStateLinearLayout = view.findViewById(R.id.data_error_state_layout);
        networkErrorStateLinearLayout = view.findViewById(R.id.network_error_state_layout);
        dataErrorStateLinearLayout.setVisibility(View.GONE);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Button refreshButton = view.findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(v -> Objects.requireNonNull(getActivity())
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new GetInvolvedFragment())
                .commit());
    }


    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.get_involved_title);
        toolbar.setNavigationIcon(R.drawable.ic_nav_open);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(GravityCompat.START);

        });
    }

    private void setupViewModel() {
        UntamedAfricaApp application = (UntamedAfricaApp) getActivity().getApplication();
        getInvolvedViewModel = ViewModelProviders.of(this, new UntamedFactory(application))
                .get(GetInvolvedViewModel.class);
        observeListOfLicences();
        observeExceptionMessage();

    }

    private void observeExceptionMessage() {
        getInvolvedViewModel.getGetInvolvedMutableLiveData().observe(this, foundationModelList -> {
            progressBar.setVisibility(View.GONE);

            if (foundationModelList != null) {
                if (foundationModelList.isEmpty()) {
                    dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
                    networkErrorStateLinearLayout.setVisibility(View.GONE);
                } else {
                    headerTextView.setVisibility(View.VISIBLE);
                    foundationsAdapter.setItems(foundationModelList);
                }
            } else {
                dataErrorStateLinearLayout.setVisibility(View.GONE);
                networkErrorStateLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void observeListOfLicences() {
        getInvolvedViewModel.getExceptionMessage().observe(this, e -> {
            progressBar.setVisibility(View.GONE);
            dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
            networkErrorStateLinearLayout.setVisibility(View.GONE);
        });
    }

}
