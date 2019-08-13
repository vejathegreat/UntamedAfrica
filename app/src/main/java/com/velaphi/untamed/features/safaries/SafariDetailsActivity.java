package com.velaphi.untamed.features.safaries;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.velaphi.untamed.R;
import com.velaphi.untamed.injection.GlideApp;

public class SafariDetailsActivity extends AppCompatActivity {

    public final static String EXTRA_SAFARI_DETAILS = "EXTRA_SAFARI_DETAILS";
    private TextView summaryTextView;
    private TextView detailsextView;
    private TextView geopointTextView;
    private TextView addressTextView;
    private ImageView mapImageView;
    private SafariModel safariModel;
    private Double lat;
    private Double lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safari_details);
        getBundles();
        setupViews();
        populateViews();

    }

    private void populateViews() {
        summaryTextView.setText(safariModel.getSummary());
        detailsextView.setText(safariModel.getDetails());
        addressTextView.setText(safariModel.getAddress());

        String url = getString(R.string.static_map_url) +
                safariModel.getCoordinates().getLatitude() +
                "," +
                safariModel.getCoordinates().getLongitude() + "&key=AIzaSyD0SXbkX4HptbJMQeU-b_hVjJdhoL5c8h8";


        GlideApp.with(this)
                .load(url)
                .centerCrop()
                .into(mapImageView);

    }

    private void setupViews() {
        summaryTextView = findViewById(R.id.summary_textview);
        detailsextView = findViewById(R.id.details_textView);
        geopointTextView = findViewById(R.id.geopoint_textview);
        addressTextView = findViewById(R.id.address_textview);
        mapImageView = findViewById(R.id.safari_map_imageview);

    }

    private void getBundles() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            safariModel = extras.getParcelable(EXTRA_SAFARI_DETAILS);
        }
    }


}
