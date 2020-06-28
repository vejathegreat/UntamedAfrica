package com.velaphi.untamed.features.old_animalDetails.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.old_animalDetails.models.Prey;
import com.velaphi.untamed.injection.GlideApp;

import java.util.List;

import static com.velaphi.untamed.utils.AppUtil.getImageFromStorage;

public class PreyAdapter extends RecyclerView.Adapter<PreyAdapter.ViewHolder> {

    private List<Prey> preyList;
    private Context context;

    @NonNull
    @Override
    public PreyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_predators, parent, false);
        this.context = view.getContext();
        return new PreyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreyAdapter.ViewHolder holder, int position) {

        Prey prey = preyList.get(position);
        holder.predatorTextView.setText(prey.getName());

        GlideApp.with(context)
                .load(getImageFromStorage(prey.getImage()))
                .fitCenter()
                .into(holder.predatorImageView);

    }

    @Override
    public int getItemCount() {
        return preyList.size();
    }

    public void setItems(List<Prey> preyList) {
        this.preyList = preyList;
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