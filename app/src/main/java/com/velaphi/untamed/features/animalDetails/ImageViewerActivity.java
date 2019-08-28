package com.velaphi.untamed.features.animalDetails;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.velaphi.untamed.R;
import com.velaphi.untamed.injection.GlideApp;

import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_URL;
import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;


public class ImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_view);
        ImageView imageView = findViewById(R.id.image_view);

        String url = getIntent().getStringExtra(EXTRA_URL);
        RequestOptions options = new RequestOptions()
                .error(R.color.black_overlay)
                .placeholder(R.color.black_overlay)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(this)
                .load(getImageFromStorage(url))
                .apply(options)
                .fitCenter()
                .into(imageView);

    }
}
