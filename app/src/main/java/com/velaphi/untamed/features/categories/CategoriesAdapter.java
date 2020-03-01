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

import java.util.ArrayList;
import java.util.List;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

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
        holder.title.setText(categoryModel.getName());
        holder.description.setText(categoryModel.getDescription());

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        GlideApp.with(context)
                .load(getImageFromStorage(categoryModel.getImage()))
                .thumbnail(0.1f)
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

    void setItems(List<com.velaphi.untamed.features.categories.CategoryModel> categoryModelList) {
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
            description = view.findViewById(R.id.summary_textview);
            bannerImage = view.findViewById(R.id.background_imageview);
        }
    }
}
