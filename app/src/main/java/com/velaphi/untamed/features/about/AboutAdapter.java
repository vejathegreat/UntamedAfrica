package com.velaphi.untamed.features.about;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;

import java.util.ArrayList;
import java.util.List;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    private List<AboutModel> aboutModelList = new ArrayList<>();

    public AboutAdapter() {
    }

    @NonNull
    @Override
    public AboutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_about, parent, false);
        return new AboutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutAdapter.ViewHolder holder, int position) {
        AboutModel aboutModel = aboutModelList.get(position);
        holder.descriptionTextView.setText(aboutModel.getDescription());
        holder.titleTextView.setText(aboutModel.getTitle());
    }

    public void setItems(List<AboutModel> aboutModelList) {
        this.aboutModelList = aboutModelList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return aboutModelList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;

        ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.title_textView);
            descriptionTextView = view.findViewById(R.id.description_textView);
        }
    }

}
