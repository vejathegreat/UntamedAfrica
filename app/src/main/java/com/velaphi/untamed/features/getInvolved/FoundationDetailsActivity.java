package com.velaphi.untamed.features.getInvolved;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.velaphi.untamed.R;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.AppUtil;

import java.util.Objects;

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
        TextView visitUsTextView = findViewById(R.id.visit_us_textView);
        visitUsTextView.setText(Html.fromHtml(foundationModel.getMainSite()));
        TextView contentTextView = findViewById(R.id.content_textView);
        contentTextView.setText(foundationModel.getContent());
        TextView tagLineTextView = findViewById(R.id.tag_textView);
        tagLineTextView.setText(foundationModel.getTagLine());
        ImageView foundationImageView = findViewById(R.id.foundation_logo_imageView);

        AppUtil appUtil = new AppUtil();
        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(this)
                .load(appUtil.getImageFromStorage(foundationModel.getImage()))
                .apply(options)
                .centerInside()
                .into(foundationImageView);
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
