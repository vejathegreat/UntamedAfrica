package com.velaphi.untamed.features.getInvolved;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.velaphi.untamed.R;
import com.velaphi.untamed.injection.GlideApp;

import java.util.Objects;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

public class FoundationDetailsActivity extends AppCompatActivity {

    public static String EXTRA_FOUNDATION_MODEL = "EXTRA_FOUNDATION_MODEL";
    private FoundationModel foundationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_details);
        getBundles();
        setupToolbar();
        setupView();
    }

    private void setupView() {
        MaterialCardView volunteerCardView = findViewById(R.id.volunteer_cardView);
        TextView contentTextView = findViewById(R.id.content_textView);
        contentTextView.setText(foundationModel.getContent());
        TextView tagLineTextView = findViewById(R.id.tag_textView);
        tagLineTextView.setText(foundationModel.getTagLine());
        ImageView foundationImageView = findViewById(R.id.foundation_logo_imageView);
//        visitUsTextView.setOnClickListener(v -> openWeb(foundationModel.getMainSite()));
        volunteerCardView.setOnClickListener(v -> openWeb(foundationModel.getHelpUrl()));

        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(this)
                .load(getImageFromStorage(foundationModel.getImage()))
                .apply(options)
                .centerInside()
                .into(foundationImageView);
    }

    private void openWeb(String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(foundationModel.getName());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getBundles() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            foundationModel = extras.getParcelable(EXTRA_FOUNDATION_MODEL);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
