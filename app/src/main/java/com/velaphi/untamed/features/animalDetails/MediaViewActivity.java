package com.velaphi.untamed.features.animalDetails;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.velaphi.untamed.R;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.AppUtil;

import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_IMAGE;
import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_URL;


public class MediaViewActivity extends AppCompatActivity {

    public static final String STEP_URI = "step_uri";
    public static final String STEP_VIDEO_POSITION = "step_video_position";
    public static final String STEP_PLAY_WHEN_READY = "step_play_when_ready";
    public static final String STEP_PLAY_WINDOW_INDEX = "step_play_window_index";
    public static final String STEP_SINGLE = "step_single";

    private String url;
    private Uri videoUrl;
    private ImageView imageView;
    private long mPlayerPosition;
    private boolean mShouldPlayWhenReady = true;
    private int mWindowIndex;

    private PlayerView video;
    private SimpleExoPlayer simpleExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_view);
        imageView = findViewById(R.id.image_view);

        url = getIntent().getStringExtra(EXTRA_URL);
        boolean isImage = getIntent().getBooleanExtra(EXTRA_IMAGE, true);

        if (isImage) {
            displayImage();
        } else {
            if (savedInstanceState != null) {
                mShouldPlayWhenReady = savedInstanceState.getBoolean(STEP_PLAY_WHEN_READY);
                mPlayerPosition = savedInstanceState.getLong(STEP_VIDEO_POSITION);
                mWindowIndex = savedInstanceState.getInt(STEP_PLAY_WINDOW_INDEX);
            } else {
                setVideo();
            }

        }

    }

    private void setVideo() {
        video = findViewById(R.id.video_view);
        video.setVisibility(View.VISIBLE);
        videoUrl = Uri.parse(url);

    }

    private void displayImage() {
        AppUtil util = new AppUtil();
        RequestOptions options = new RequestOptions()
                .error(R.color.black_overlay)
                .placeholder(R.color.black_overlay)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(this)
                .load(util.getImageFromStorage(url))
                .apply(options)
                .fitCenter()
                .into(imageView);
    }
}
