package com.velaphi.untamed.features.animalDetails;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.velaphi.untamed.R;
import com.velaphi.untamed.injection.GlideApp;

import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_URL;
import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;


public class ImageViewerActivity extends AppCompatActivity {
    PhotoView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_view);
        ImageView imageView = findViewById(R.id.image_view);

        String url = getIntent().getStringExtra(EXTRA_URL);
        if (savedInstanceState == null) {
            RequestOptions options = new RequestOptions()
                    .error(R.color.black_overlay)
                    .placeholder(R.color.black_overlay)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH);
            GlideApp.with(this).load(getImageFromStorage(url)).apply(options).fitCenter().into(imageView);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        outState.putParcelable("image", bitmap);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Bitmap image = savedInstanceState.getParcelable("image");
        GlideApp.with(this)
                .load(image)
                .fitCenter()
                .into(imageView);
    }


}