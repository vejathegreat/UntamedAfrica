package com.velaphi.untamed.features.animalDetails.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.VideoPlayerActivity;
import com.velaphi.untamed.features.animalDetails.models.Video;
import com.velaphi.untamed.injection.GlideApp;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private ArrayList<Video> videoList;
    private Context context;
    private boolean showMin;

    public VideosAdapter(Context context, ArrayList<Video> videoList, boolean showMin) {
        this.context = context;
        this.videoList = videoList;
        this.showMin = showMin;
    }

    @NonNull
    @Override
    public VideosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_video, parent, false);
        return new VideosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.ViewHolder holder, int position) {
        Video video = videoList.get(position);

        holder.captionTextView.setText(video.getCaption());
        holder.sourceTextView.setText("Source: " + video.getSource());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra(VideoPlayerActivity.EXTRA_VIDEO_URL, video.getUrl());
            context.startActivity(intent);
        });


        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        RequestBuilder<Drawable> thumbnailRequest = GlideApp
                .with(context)
                .load(video.getUrl());

        GlideApp.with(context)
                .load(video.getUrl())
                .thumbnail(thumbnailRequest)
                .apply(options)
                .centerCrop()
                .into(holder.thumbnailImageView);

    }

    @Override
    public int getItemCount() {

        if (showMin) {
            if (videoList != null && videoList.size() < 3) {
                return videoList.size();
            } else {
                return 3;
            }

        }

        return videoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView captionTextView;
        TextView sourceTextView;
        ImageView thumbnailImageView;

        ViewHolder(View view) {
            super(view);
            captionTextView = view.findViewById(R.id.caption_textView);
            sourceTextView = view.findViewById(R.id.source_textView);
            thumbnailImageView = view.findViewById(R.id.thumbnail_imageView);
        }
    }
}

