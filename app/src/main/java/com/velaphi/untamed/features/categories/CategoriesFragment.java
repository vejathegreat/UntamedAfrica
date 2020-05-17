package com.velaphi.untamed.features.categories;

import android.content.res.Configuration;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.injection.UntamedFactory;

import java.util.Objects;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel categoriesViewModel;
    private CategoriesAdapter categoriesAdapter;
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
        categoriesViewModel.retrieveListOfCategoriesFromFirebase();
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
                .replace(R.id.fragment_container, new CategoriesFragment())
                .commit());
    }

    private void setupRecyclerview(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        int orientation = getActivity().getResources().getConfiguration().orientation;
        RecyclerView categoriesRecyclerView = view.findViewById(R.id.recyclerview_generic);
        categoriesAdapter = new CategoriesAdapter(getContext());

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(getActivity());
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int LANDSCAPE_SPAN_COUNT = 2;
            layoutManager = new GridLayoutManager(getActivity(), LANDSCAPE_SPAN_COUNT);
        }

        categoriesRecyclerView.setLayoutManager(layoutManager);
        categoriesRecyclerView.setNestedScrollingEnabled(false);
        categoriesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView toolBarTitleTextView = view.findViewById(R.id.toolbar_title);
        toolBarTitleTextView.setText(R.string.categories_screen_title);
//        toolbar.setTitle(R.string.categories_screen_title);
//        toolbar.setNavigationIcon(R.drawable.ic_nav_open);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(v -> {
//            DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
//            drawerLayout.openDrawer(GravityCompat.START);
//
//        });
    }

    private void setupViewModel() {
        UntamedAfricaApp application = (UntamedAfricaApp) getActivity().getApplication();
        categoriesViewModel = ViewModelProviders.of(this, new UntamedFactory(application))
                .get(CategoriesViewModel.class);
        observeListOfCategories();
        observeExceptionMessage();
    }

    private void observeExceptionMessage() {

        categoriesViewModel.getExceptionMessage().observe(this, e -> {
            progressBar.setVisibility(View.GONE);
            dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
            networkErrorStateLinearLayout.setVisibility(View.GONE);
        });

    }

    private void observeListOfCategories() {
        categoriesViewModel.getCategoryListLiveData().observe(this, categoryModelList -> {
            progressBar.setVisibility(View.GONE);

            if (categoryModelList != null) {
                if (categoryModelList.isEmpty()) {
                    dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
                    networkErrorStateLinearLayout.setVisibility(View.GONE);
                } else {
                    categoriesAdapter.setItems(categoryModelList);
                }
            } else {
                dataErrorStateLinearLayout.setVisibility(View.GONE);
                networkErrorStateLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}
