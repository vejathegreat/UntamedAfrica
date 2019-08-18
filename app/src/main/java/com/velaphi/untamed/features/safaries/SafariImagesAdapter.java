package com.velaphi.untamed.features.safaries;

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
import com.velaphi.untamed.features.animalDetails.MediaViewActivity;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_IMAGE;
import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_URL;

public class SafariImagesAdapter extends RecyclerView.Adapter<SafariImagesAdapter.ViewHolder> {
    private List<String> imageList = new ArrayList<>();

    public SafariImagesAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_safari_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String imageUrl = imageList.get(position);
        AppUtil appUtil = new AppUtil();
        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(holder.itemView.getContext())
                .load(appUtil.getImageFromStorage(imageUrl))
                .apply(options)
                .centerCrop()
                .into(holder.safariImageView);

        holder.safariImageView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, MediaViewActivity.class);
            intent.putExtra(EXTRA_URL, imageUrl);
            intent.putExtra(EXTRA_IMAGE, true);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    void setItems(List<String> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView safariImageView;

        ViewHolder(View view) {
            super(view);
            safariImageView = view.findViewById(R.id.safari_imageview);
        }
    }
}
