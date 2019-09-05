package com.velaphi.untamed.features.animalDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.velaphi.untamed.R;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.features.animalDetails.models.Fact;
import com.velaphi.untamed.features.widget.AppWidgetService;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.injection.UntamedFactory;

import java.util.Objects;
import java.util.Random;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

public class AnimalDetailsActivity extends AppCompatActivity {

    public final static String EXTRA_ANIMAL_DETAILS = "EXTRA_ANIMAL_DETAILS";
    boolean isExpanded = false;
    private AnimalDetailsModel animalDetailsModel;
    private LinearLayout habitatLinearLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int shortAnimationDuration;
    private Fragment fragment = null;
    private FloatingActionButton favouriteFab;
    private AnimalDetailsViewModel animalDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);
        getBundles();

        if (savedInstanceState == null) {
            setupTabs();
            setupToolbar();
            setAnimalDetails();
            setupFab();
        }


    }

    private void setupFab() {
        favouriteFab = findViewById(R.id.favorite);
        UntamedAfricaApp application = (UntamedAfricaApp) this.getApplication();
        animalDetailsViewModel = ViewModelProviders.of(this, new UntamedFactory(application)).get(AnimalDetailsViewModel.class);
        animalDetailsViewModel.retrieveIsFavorite(animalDetailsModel.getName());
        favouriteFab.setOnClickListener(v -> animalDetailsViewModel.toggleFab(animalDetailsModel));
        animalDetailsViewModel.isFavourite.observe(this, animalDetailsModel -> {
            if (animalDetailsModel != null) {
                updateFab(true);
            } else {
                updateFab(false);
            }
        });
    }

    private void updateFab(Boolean isFavorite) {
        if (isFavorite) {
            favouriteFab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_filled));
        } else {
            favouriteFab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_empty));
        }

    }


    private void setupTabs() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        fragment = new FactsFragment();
        replaceFragment();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new FactsFragment();
                        break;

                    case 1:
                        fragment = new GalleryFragment();
                        break;

                    case 2:
                        fragment = new OtherFragment();
                        break;
                }

                replaceFragment();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void replaceFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ANIMAL_DETAILS, animalDetailsModel);
        fragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.tab_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }


    private void setAnimalDetails() {
        habitatLinearLayout = findViewById(R.id.habitat_layout);
        ImageView bannerImageView = findViewById(R.id.banner_imageView);
        TextView quotableTextView = findViewById(R.id.quotable_textView);
        TextView descriptionHeaderTextview = findViewById(R.id.heading_description_textView);
        TextView descriptionDetailsTextview = findViewById(R.id.details_description_textView);

        TextView habitatHeadertextview = findViewById(R.id.heading_habitat_textView);
        TextView habitatDetailsTextview = findViewById(R.id.details_habitat_textView);
        Random randomElement = new Random();
        Fact fact = animalDetailsModel.getFacts().get(randomElement.nextInt(animalDetailsModel.getFacts().size()));
        AppWidgetService.updateWidget(this, fact);
        StringBuilder habitat = new StringBuilder();
        habitat.append(getString(R.string.habitat));
        habitatDetailsTextview.setText(animalDetailsModel.getHabitat().getDescription());
        habitatHeadertextview.setText(habitat);

        Button readMoreButton = findViewById(R.id.read_more_button);
        final int MAX_LINES = 50;
        final int MIN_LINES = 4;

        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_mediumAnimTime);

        descriptionHeaderTextview.setText(animalDetailsModel.getDescription().getHeading());
        descriptionDetailsTextview.setText(animalDetailsModel.getDescription().getDetails());

        readMoreButton.setOnClickListener(v -> {
            if (isExpanded) {
                descriptionDetailsTextview.setMaxLines(MIN_LINES);
                isExpanded = false;
                habitatLinearLayout.setVisibility(View.GONE);
                readMoreButton.setText(R.string.read_more);
            } else {
                isExpanded = true;
                descriptionDetailsTextview.setMaxLines(MAX_LINES);
                habitatLinearLayout.setVisibility(View.VISIBLE);
                habitatLinearLayout.setAlpha(0f);
                habitatLinearLayout.setVisibility(View.VISIBLE);

                habitatLinearLayout.animate()
                        .alpha(1f)
                        .setDuration(shortAnimationDuration)
                        .setListener(null);
                readMoreButton.setText(R.string.read_less);
            }
        });

        quotableTextView.setText(animalDetailsModel.getQuotable());
        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(this)
                .load(getImageFromStorage(animalDetailsModel.getImage()))
                .apply(options)
                .centerCrop()
                .into(bannerImageView);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(animalDetailsModel.getName());


    }

    private void getBundles() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            animalDetailsModel = extras.getParcelable(EXTRA_ANIMAL_DETAILS);
        }
    }
}
