package com.velaphi.untamed.features.animalList;

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
import com.velaphi.untamed.features.animalDetails.AnimalDetailsActivity;
import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.AppUtil;

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

        AppUtil appUtil = new AppUtil();
        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        GlideApp.with(context)
                .load(appUtil.getImageFromStorage(animalDetailsModel.getImage()))
                .apply(options)
                .centerCrop()
                .into(holder.bannerImageView);

        holder.itemView.setOnClickListener(v -> {
            Intent animalDetailsIntent = new Intent(context, AnimalDetailsActivity.class);
            animalDetailsIntent.putExtra(AnimalDetailsActivity.EXTRA_ANIMAL_DETAILS, animalDetailsModel);
            context.startActivity(animalDetailsIntent);
        });
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
