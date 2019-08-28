package com.velaphi.untamed.features.animalDetails.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.ImageViewerActivity;
import com.velaphi.untamed.injection.GlideApp;

import java.util.List;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<String> imageList;
    private Context context;

    public static String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static String EXTRA_URL = "EXTRA_URL";

    public ImagesAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_animal_layout, parent, false);
        return new ImagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = imageList.get(position);

        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(context)
                .load(getImageFromStorage(imageUrl))
                .apply(options)
                .centerCrop()
                .into(holder.animalImage);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ImageViewerActivity.class);
            intent.putExtra(EXTRA_URL, imageUrl);
            intent.putExtra(EXTRA_IMAGE, true);
            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {

        final int MAX_VIDEOS = 8;
        if (imageList.size() > MAX_VIDEOS) {

            return MAX_VIDEOS;
        } else {
            return imageList.size();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView animalImage;

        ViewHolder(View view) {
            super(view);
            animalImage = view.findViewById(R.id.animal_imageview);
        }
    }
}