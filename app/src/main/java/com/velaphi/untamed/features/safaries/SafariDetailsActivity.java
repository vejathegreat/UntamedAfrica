package com.velaphi.untamed.features.safaries;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.CirclePagerIndicatorDecoration;

public class SafariDetailsActivity extends AppCompatActivity {

    public final static String EXTRA_SAFARI_DETAILS = "EXTRA_SAFARI_DETAILS";
    private TextView summaryTextView;
    private TextView detailsTextView;
    private TextView geoPointTextView;
    private TextView addressTextView;
    private ImageView mapImageView;
    private SafariModel safariModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safari_details);
        getBundles();
        setupViews();
        setupImages();
        setupToolbar();
        populateViews();

    }

    private void setupImages() {
        RecyclerView safariImagesRecyclerView = findViewById(R.id.images_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        SafariImagesAdapter safariImagesAdapter = new SafariImagesAdapter();

        safariImagesRecyclerView.setLayoutManager(layoutManager);
        safariImagesRecyclerView.setNestedScrollingEnabled(false);
        safariImagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        safariImagesRecyclerView.setAdapter(safariImagesAdapter);
        safariImagesRecyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        safariImagesAdapter.setItems(safariModel.getImageList());
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(safariModel.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void populateViews() {
        summaryTextView.setText(safariModel.getSummary());
        detailsTextView.setText(safariModel.getDetails().replaceAll("--", "\n"));
        addressTextView.setText(safariModel.getAddress());
        String url = getString(R.string.static_map_url) +
                safariModel.getCoordinates().getLatitude() +
                " , " +
                safariModel.getCoordinates().getLongitude() + "&key=AIzaSyD0SXbkX4HptbJMQeU-b_hVjJdhoL5c8h8";

        GlideApp.with(this)
                .load(url)
                .centerCrop()
                .into(mapImageView);
        geoPointTextView.setText(safariModel.getCoordinates().toString());

    }

    private void setupViews() {
        summaryTextView = findViewById(R.id.summary_textview);
        detailsTextView = findViewById(R.id.details_textView);
        geoPointTextView = findViewById(R.id.geopoint_textview);
        addressTextView = findViewById(R.id.address_textview);
        mapImageView = findViewById(R.id.safari_map_imageview);
        Button moreInfoButton = findViewById(R.id.more_info);
        moreInfoButton.setOnClickListener(v -> openWeb());
        mapImageView.setOnClickListener(v -> openMaps());

    }

    private void openWeb() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(safariModel.getWeb()));
        startActivity(i);
    }

    private void getBundles() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            safariModel = extras.getParcelable(EXTRA_SAFARI_DETAILS);
        }
    }

    void openMaps() {
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" +
                safariModel.getCoordinates().getLatitude() +
                " , " +
                safariModel.getCoordinates().getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
