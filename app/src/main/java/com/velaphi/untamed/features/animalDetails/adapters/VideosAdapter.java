package com.velaphi.untamed.features.animalDetails.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.MediaViewActivity;
import com.velaphi.untamed.features.animalDetails.models.Video;
import com.velaphi.untamed.injection.GlideApp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_IMAGE;
import static com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter.EXTRA_URL;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private final int MAX_VIDEOS = 5;
    private List<Video> videoList;
    private Context context;

    public VideosAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
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


        URL thumbnailUrl = null;
        try {
            thumbnailUrl = getThumbnail(video);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        holder.captionTextView.setText(video.getCaption());
        holder.sourceTextView.setText("Source: " + video.getSource());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, MediaViewActivity.class);
            intent.putExtra(EXTRA_URL, video.getUrl());
            intent.putExtra(EXTRA_IMAGE, false);
            context.startActivity(intent);
        });


        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(context)
                .load(thumbnailUrl)
                .apply(options)
                .centerCrop()
                .into(holder.thumbnailImageView);

    }

    private URL getThumbnail(Video video) throws MalformedURLException {
        if (video.getType().equals("YouTube")) {
            Uri uri = Uri.parse(video.getUrl());
            String videoID = uri.getQueryParameter("v");
            return new URL(("https://img.youtube.com/vi/") + (videoID) + ("/0.jpg"));
        }

        return null;
    }

    @Override
    public int getItemCount() {

        if (videoList.size() > MAX_VIDEOS) {

            return MAX_VIDEOS;
        } else {
            return videoList.size();
        }

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

