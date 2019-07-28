package com.velaphi.untamed.features.animalList;

import android.content.Context;
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
import com.velaphi.untamed.features.animalList.models.AnimalDetailsModel;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.ViewHolder> {
    private List<AnimalDetailsModel> animalDetailsModelList = new ArrayList<>();
    private Context context;

    public AnimalListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AnimalListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_animal, parent, false);
        return new AnimalListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnimalListAdapter.ViewHolder holder, int position) {

        AnimalDetailsModel animalDetailsModel = animalDetailsModelList.get(position);
        holder.nameTextView.setText(animalDetailsModel.getName());

        Util util = new Util();
        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        GlideApp.with(context)
                .load(util.getImageFromStorage(animalDetailsModel.getImage()))
                .apply(options)
                .centerCrop()
                .into(holder.bannerImageView);
    }

    void setItems(List<AnimalDetailsModel> animalDetailsModelList) {
        this.animalDetailsModelList = animalDetailsModelList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return animalDetailsModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView bannerImageView;

        ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name_textView);
            bannerImageView = view.findViewById(R.id.background_imageview);
        }
    }
}
