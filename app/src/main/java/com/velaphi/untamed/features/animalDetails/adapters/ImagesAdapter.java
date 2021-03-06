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
import com.velaphi.untamed.injection.GlideApp;

import java.util.ArrayList;
import java.util.List;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<String> imageList;
    private Context context;
    private boolean showMin;

    public static String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static String EXTRA_URL = "EXTRA_URL";
    public static String EXTRA_POSITION = "EXTRA_POSITION";
    public static String EXTRA_IMAGE_LIST = "EXTRA_IMAGE_LIST";
    private int position;

    public ImagesAdapter(Context context, boolean showMin) {
        this.context = context;
        this.showMin = showMin;
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
//
//            Intent intent = new Intent(context, ImageViewerActivity.class);
//            intent.putExtra(EXTRA_URL, imageUrl);
//            intent.putExtra(EXTRA_IMAGE, true);
//            intent.putStringArrayListExtra(EXTRA_IMAGE_LIST, (ArrayList<String>) imageList);
//            intent.putExtra(EXTRA_POSITION, position);
//            context.startActivity(intent);
        });

    }

    public void setItems(List<String> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {

        if (showMin) {
            if (imageList != null && imageList.size() >= 6) {
                return 6;
            } else {
                return imageList.size();
            }
        }

        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView animalImage;

        ViewHolder(View view) {
            super(view);
            animalImage = view.findViewById(R.id.animal_imageview);
        }
    }
}