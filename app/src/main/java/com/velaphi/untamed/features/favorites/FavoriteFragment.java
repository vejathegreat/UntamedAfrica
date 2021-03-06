package com.velaphi.untamed.features.favorites;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.annotations.Nullable;
import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.features.animalList.AnimalListAdapter;
import com.velaphi.untamed.features.animalList.AnimalListViewModel;
import com.velaphi.untamed.injection.UntamedFactory;

public class FavoriteFragment extends Fragment {

    private AnimalListViewModel animalListViewModel;
    private LinearLayout noAnimalsLinearLayout;
    private RecyclerView favAnimalsRecyclerView;
    private AnimalListAdapter animalListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        setupToolbar(view);
        setupView(view);
        setupViewModel();
        setupRecyclerview(view);
        return view;
    }

    private void setupView(View view) {
        noAnimalsLinearLayout = view.findViewById(R.id.no_favs_linear_layout);
        favAnimalsRecyclerView = view.findViewById(R.id.favorites_recyclerView);
    }


    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu_favorite);
        toolbar.setNavigationIcon(R.drawable.ic_nav_open);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(GravityCompat.START);

        });
    }

    private void setupViewModel() {
        UntamedAfricaApp application = (UntamedAfricaApp) getActivity().getApplication();
        animalListViewModel = ViewModelProviders.of(this, new UntamedFactory(application))
                .get(AnimalListViewModel.class);
        observeListOfAnimals();
    }

    private void observeListOfAnimals() {
        animalListViewModel.retrieveFavMovies().observe(this, animalDetailsModelList -> {

            if (animalDetailsModelList != null) {
                if (animalDetailsModelList.isEmpty()) {
                    noAnimalsLinearLayout.setVisibility(View.VISIBLE);
                    animalListAdapter.clearList();
                } else {
                    noAnimalsLinearLayout.setVisibility(View.GONE);
                    favAnimalsRecyclerView.setVisibility(View.VISIBLE);
                    animalListAdapter.setItems(animalDetailsModelList);

                }
            }
        });
    }

    private void setupRecyclerview(View view) {
        RecyclerView animalsRecyclerView = view.findViewById(R.id.favorites_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        int orientation = this.getResources().getConfiguration().orientation;

        animalListAdapter = new AnimalListAdapter(getContext());

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(getContext());
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int LANDSCAPE_SPAN_COUNT = 2;
            layoutManager = new GridLayoutManager(getContext(), LANDSCAPE_SPAN_COUNT);
        }

        animalsRecyclerView.setLayoutManager(layoutManager);
        animalsRecyclerView.setNestedScrollingEnabled(false);
        animalsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        animalsRecyclerView.setAdapter(animalListAdapter);
    }


}
