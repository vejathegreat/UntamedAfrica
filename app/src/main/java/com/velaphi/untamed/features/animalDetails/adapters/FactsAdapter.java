package com.velaphi.untamed.features.animalDetails.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.models.Fact;

import java.util.ArrayList;
import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.ViewHolder> {
    private List<Fact> factList = new ArrayList<>();
    private Context context;

    public FactsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_facts, parent, false);
        return new FactsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fact fact = factList.get(position);
        holder.descriptionTextView.setText(fact.getDescription());
        holder.titleTextView.setText(fact.getTitle());
    }

    public void setItems(List<Fact> factList) {
        this.factList = factList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return factList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;

        ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.title_textview);
            descriptionTextView = view.findViewById(R.id.description_textview);
        }
    }
}
