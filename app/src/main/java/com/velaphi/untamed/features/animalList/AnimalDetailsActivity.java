package com.velaphi.untamed.features.animalList;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalList.models.AnimalDetailsModel;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.Util;

import java.util.Objects;

public class AnimalDetailsActivity extends AppCompatActivity {

    public final static String EXTRA_ANIMAL_DETAILS = "EXTRA_ANIMAL_DETAILS";
    boolean isExpanded = false;
    private AnimalDetailsModel animalDetailsModel;
    private LinearLayout habitatLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);
        getBundles();
        setupToolbar();
        populateView();
        setPredators();
        setAnimalDetails();

        FloatingActionButton favourite = findViewById(R.id.favorite);
        favourite.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void setAnimalDetails() {
        habitatLinearLayout = findViewById(R.id.habitat_layout);
        TextView descriptionHeaderTextview = findViewById(R.id.heading_description_textView);
        TextView descriptionDetailsTextview = findViewById(R.id.details_description_textView);

        TextView habitatHeadertextview = findViewById(R.id.heading_habitat_textView);
        TextView habitatDetailsTextview = findViewById(R.id.details_habitat_textView);

        StringBuilder habitat = new StringBuilder();
        habitat.append(getString(R.string.habitat)).append(" ").append(animalDetailsModel.getHabitat().getSummary());
        habitatDetailsTextview.setText(animalDetailsModel.getHabitat().getDescription());
        habitatHeadertextview.setText(habitat);

        Button readMoreButton = findViewById(R.id.read_more_button);

        final int MAX_LINES = 50;
        final int MIN_LINES = 4;

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
                readMoreButton.setText(R.string.read_less);
            }
        });

    }

    private void setPredators() {
        final ChipGroup chipGroup = findViewById(R.id.predators_group);
        for (String predator : animalDetailsModel.getPredators()) {
            final Chip chip = new Chip(this);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics());

            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            chip.setText(predator);
            chipGroup.addView(chip);
        }
    }

    private void populateView() {
        TextView scientificNames = findViewById(R.id.scientific_names_textView);
        String circle = "\u25CF";
        StringBuilder names = new StringBuilder();

        for (String name : animalDetailsModel.getScientificNames()) {
            names.append(circle).append(" ")
                    .append(name).append("\t\t");
        }
        scientificNames.setText(names);


        StringBuilder weight = new StringBuilder();
        TextView weightTextView = findViewById(R.id.weight_textView);
        for (String weight_index : animalDetailsModel.getWeight()) {
            weight.append(circle).append(" ")
                    .append(weight_index).append("\n");
        }
        weightTextView.setText(weight);


        StringBuilder size = new StringBuilder();
        TextView sizeTextView = findViewById(R.id.size_textView);
        size.append(circle).append(" ").append(animalDetailsModel.getSize());
        sizeTextView.setText(size);

        StringBuilder lifeSpan = new StringBuilder();
        TextView lifespanTextView = findViewById(R.id.lifespan_textView);
        lifeSpan.append(circle).append(" ").append(animalDetailsModel.getLifeSpan());
        lifespanTextView.setText(lifeSpan);

        StringBuilder diet = new StringBuilder();
        TextView dietTextView = findViewById(R.id.diet_textView);
        diet.append(circle).append(" ").append(animalDetailsModel.getDiet());
        dietTextView.setText(diet);

        StringBuilder gestation = new StringBuilder();
        TextView gestationTextView = findViewById(R.id.gestation_textView);
        gestation.append(circle).append(" ").append(animalDetailsModel.getGestation());
        gestationTextView.setText(gestation);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView bannerImageView = findViewById(R.id.banner_imageView);
        TextView quotableTextView = findViewById(R.id.quotable_textView);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(animalDetailsModel.getName());

        quotableTextView.setText(animalDetailsModel.getQuotable());
        Util util = new Util();
        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(this)
                .load(util.getImageFromStorage(animalDetailsModel.getImage()))
                .apply(options)
                .centerCrop()
                .into(bannerImageView);
    }

    private void getBundles() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            animalDetailsModel = extras.getParcelable(EXTRA_ANIMAL_DETAILS);
        }
    }


}
