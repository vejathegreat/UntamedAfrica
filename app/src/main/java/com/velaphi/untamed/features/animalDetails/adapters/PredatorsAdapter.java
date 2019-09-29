package com.velaphi.untamed.features.animalDetails.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.models.Predator;
import com.velaphi.untamed.injection.GlideApp;

import java.util.List;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

public class PredatorsAdapter extends RecyclerView.Adapter<PredatorsAdapter.ViewHolder> {

    private List<Predator> predatorList;
    private Context context;

    public PredatorsAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_predators, parent, false);
        this.context = view.getContext();
        return new PredatorsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Predator predator = predatorList.get(position);
        holder.predatorTextView.setText(predator.getName());

        GlideApp.with(context)
                .load(getImageFromStorage(predator.getImage()))
                .fitCenter()
                .into(holder.predatorImageView);

    }

    @Override
    public int getItemCount() {
        return predatorList.size();
    }

    public void setItems(List<Predator> predatorList) {
        this.predatorList = predatorList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView predatorTextView;
        ImageView predatorImageView;

        ViewHolder(View view) {
            super(view);
            predatorTextView = view.findViewById(R.id.textView_predator_name);
            predatorImageView = view.findViewById(R.id.predator_imageView);
        }
    }
}
