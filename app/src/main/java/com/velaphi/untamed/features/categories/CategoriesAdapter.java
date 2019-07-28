package com.velaphi.untamed.features.categories;

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
import com.velaphi.untamed.features.animalList.AnimalListActivity;
import com.velaphi.untamed.injection.GlideApp;
import com.velaphi.untamed.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private Context context;

    public CategoriesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        CategoryModel categoryModel = categoryModelList.get(position);
        Util util = new Util();
        holder.title.setText(categoryModel.getName());
        holder.description.setText(categoryModel.getDescription());

        RequestOptions options = new RequestOptions()
                .error(R.color.colorAccent)
                .placeholder(R.color.colorAccent)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(context)
                .load(util.getImageFromStorage(categoryModel.getImage()))
                .apply(options)
                .centerCrop()
                .into(holder.bannerImage);

        holder.itemView.setOnClickListener(v -> {
            Intent openAnimalListIntent = new Intent(context, AnimalListActivity.class);
            openAnimalListIntent.putExtra(AnimalListActivity.EXTRA_CATEGORY_NAME, categoryModel.getName());
            openAnimalListIntent.putExtra(AnimalListActivity.EXTRA_CATEGORY_LEVEL, categoryModel.getLevel());
            context.startActivity(openAnimalListIntent);
        });
    }

    void setItems(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView bannerImage;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.textview_title);
            description = view.findViewById(R.id.textView_description);
            bannerImage = view.findViewById(R.id.background_imageview);
        }
    }
}
