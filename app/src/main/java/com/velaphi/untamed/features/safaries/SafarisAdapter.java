package com.velaphi.untamed.features.safaries;

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

public class SafarisAdapter extends RecyclerView.Adapter<SafarisAdapter.ViewHolder> {
    private List<SafariModel> safariModelList = new ArrayList<>();
    private Context context;

    public SafarisAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SafarisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_safari, parent, false);
        return new SafarisAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SafarisAdapter.ViewHolder holder, int position) {

        SafariModel safariModel = safariModelList.get(position);
        holder.name.setText(safariModel.getName());
        holder.summary.setText(safariModel.getSummary());

        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(context)
                .load(getImageFromStorage(safariModel.getImage()))
                .apply(options)
                .centerCrop()
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent safariDetailsIntent = new Intent(context, SafariDetailsActivity.class);
            safariDetailsIntent.putExtra(SafariDetailsActivity.EXTRA_SAFARI_DETAILS, safariModel);
            context.startActivity(safariDetailsIntent);
        });
    }

    void setItems(List<SafariModel> safariModelList) {
        this.safariModelList = safariModelList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return safariModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView summary;
        ImageView image;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.safari_name_textview);
            summary = view.findViewById(R.id.summary_textview);
            image = view.findViewById(R.id.background_imageview);
        }
    }
}
