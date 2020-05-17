package com.velaphi.untamed.features.animalList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.injection.UntamedFactory;

import java.util.Objects;



public class AnimalListActivity extends AppCompatActivity {

    public final static String EXTRA_CATEGORY_NAME = "EXTRA_CATEGORY_NAME";
    public final static String EXTRA_CATEGORY_LEVEL = "EXTRA_CATEGORY_LEVEL";
    ProgressBar progressBar;
    LinearLayout dataErrorStateLinearLayout;
    LinearLayout networkErrorStateLinearLayout;
    private AnimalListViewModel animalListViewModel;
    private String categoryName;
    private AnimalListAdapter animalListAdapter;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);
        getBundles();
        setupViews();
        setupToolbar();
        setupViewModel();
        setupRecyclerView();
        animalListViewModel.retrieveListOfAnimalsFromFirebase(level);
    }

    private void setupViews() {
        progressBar = findViewById(R.id.progressBar);
        dataErrorStateLinearLayout = findViewById(R.id.data_error_state_layout);
        networkErrorStateLinearLayout = findViewById(R.id.network_error_state_layout);
        Button refreshButton = findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(v -> startActivity(new Intent(this, AnimalListActivity.class)));
    }

    private void getBundles() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categoryName = extras.getString(EXTRA_CATEGORY_NAME);
            level = extras.getInt(EXTRA_CATEGORY_LEVEL);
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(categoryName);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupViewModel() {
        UntamedAfricaApp application = (UntamedAfricaApp) getApplication();
        animalListViewModel = ViewModelProviders.of(this, new UntamedFactory(application))
                .get(AnimalListViewModel.class);
        observeListOfAnimals();
        observeExceptionMessage();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void observeExceptionMessage() {
        animalListViewModel.getExceptionMessage().observe(this, e -> {
            progressBar.setVisibility(View.GONE);
            dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
            networkErrorStateLinearLayout.setVisibility(View.GONE);
        });

    }

    private void observeListOfAnimals() {

        animalListViewModel.getAnimalDetailListLiveData().observe(this, animalDetailsModelList -> {
            progressBar.setVisibility(View.GONE);

            if (animalDetailsModelList != null) {
                if (animalDetailsModelList.isEmpty()) {
                    dataErrorStateLinearLayout.setVisibility(View.VISIBLE);
                    networkErrorStateLinearLayout.setVisibility(View.GONE);
                } else {
                    animalListAdapter.setItems(animalDetailsModelList);
                }
            } else {
                dataErrorStateLinearLayout.setVisibility(View.GONE);
                networkErrorStateLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView animalsRecyclerView = findViewById(R.id.recyclerview_animal_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        int orientation = this.getResources().getConfiguration().orientation;

        animalListAdapter = new AnimalListAdapter(this);

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(this);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int LANDSCAPE_SPAN_COUNT = 2;
            layoutManager = new GridLayoutManager(this, LANDSCAPE_SPAN_COUNT);
        }

        animalsRecyclerView.setLayoutManager(layoutManager);
        animalsRecyclerView.setNestedScrollingEnabled(false);
        animalsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        animalsRecyclerView.setAdapter(animalListAdapter);
    }


}
