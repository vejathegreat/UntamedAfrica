package com.velaphi.untamed.features.licenses;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.injection.UntamedFactory;

import java.util.Objects;

import static android.widget.LinearLayout.VERTICAL;

public class OpenSourceLicensesFragment extends Fragment {


    private LicencesViewModel licencesViewModel;
    private LicensesAdapter licensesAdapter;
    private ProgressBar progressBar;
    private LinearLayout dataErrorStateLinearLayout;
    private LinearLayout networkErrorStateLinearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generic_with_recyclerview, container, false);
        setupToolbar(view);
        setupViewModel();
        setupRecyclerview(view);
        setupView(view);
        licencesViewModel.retrieveListOfLicencesFromFirebase();
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
                .replace(R.id.fragment_container, new OpenSourceLicensesFragment())
                .commit());
    }

    private void setupRecyclerview(View view) {
        RecyclerView licensesRecyclerView = view.findViewById(R.id.recyclerview_generic);
        licensesAdapter = new LicensesAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        licensesRecyclerView.setLayoutManager(layoutManager);
        licensesRecyclerView.setNestedScrollingEnabled(false);
        licensesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        licensesRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), VERTICAL));
        licensesRecyclerView.setAdapter(licensesAdapter);
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.licences_title);
        toolbar.setNavigationIcon(R.drawable.ic_nav_open);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(GravityCompat.START);

        });
    }

    private void setupViewModel() {
        UntamedAfricaApp application = (UntamedAfricaApp) getActivity().getApplication();
        licencesViewModel = ViewModelProviders.of(this, new UntamedFactory(application))
                .get(LicencesViewModel.class);
        observeListOfLicences();
        observeExceptionMessage();
    }

    private void observeExceptionMessage() {

        licencesViewModel.getExceptionMessage().observe(this, e -> {
            progressBar.setVisibility(View.GONE);
            dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
            networkErrorStateLinearLayout.setVisibility(View.GONE);
        });

    }

    private void observeListOfLicences() {
        licencesViewModel.getLicenceListLiveData().observe(this, licenceModelList -> {
            progressBar.setVisibility(View.GONE);

            if (licenceModelList != null) {
                if (licenceModelList.isEmpty()) {
                    dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
                    networkErrorStateLinearLayout.setVisibility(View.GONE);
                } else {
                    licensesAdapter.setItems(licenceModelList);
                }
            } else {
                dataErrorStateLinearLayout.setVisibility(View.GONE);
                networkErrorStateLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}
