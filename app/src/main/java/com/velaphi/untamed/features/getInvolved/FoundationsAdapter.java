package com.velaphi.untamed.features.getInvolved;

import android.content.Context;
import android.content.Intent;
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
import com.velaphi.untamed.injection.GlideApp;

import java.util.ArrayList;
import java.util.List;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

public class FoundationsAdapter extends RecyclerView.Adapter<FoundationsAdapter.ViewHolder> {

    private List<FoundationModel> foundationModelList = new ArrayList<>();
    private RequestOptions options;

    public FoundationsAdapter() {
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_foundation, parent, false);
        return new FoundationsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoundationModel foundationModel = foundationModelList.get(position);
        holder.foundationNameTexView.setText(foundationModel.getName());
        holder.foundationMissionTexView.setText(foundationModel.getMission());

        Context context = holder.itemView.getContext();
        RequestOptions options = new RequestOptions()
                .error(R.color.white)
                .placeholder(R.color.white)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(context)
                .load(getImageFromStorage(foundationModel.getImage()))
                .apply(options)
                .centerInside()
                .into(holder.foundationImageView);

        holder.itemView.setOnClickListener(v -> {
            Intent openFoundationDetails = new Intent(context, FoundationDetailsActivity.class);
            openFoundationDetails.putExtra(FoundationDetailsActivity.EXTRA_FOUNDATION_MODEL, foundationModel);
            context.startActivity(openFoundationDetails);
        });


    }


    void setItems(List<FoundationModel> foundationModelList) {
        this.foundationModelList = foundationModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foundationModelList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foundationImageView;
        TextView foundationNameTexView;
        TextView foundationMissionTexView;

        ViewHolder(View view) {
            super(view);
            foundationImageView = view.findViewById(R.id.foundation_logo_imageView);
            foundationNameTexView = view.findViewById(R.id.foundation_name_textView);
            foundationMissionTexView = view.findViewById(R.id.foundation_mission_textView);
        }
    }
}
