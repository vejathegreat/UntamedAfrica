package com.velaphi.untamed.features.animalDetails.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.models.Info;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BasicInformationAdapter extends RecyclerView.Adapter<BasicInformationAdapter.ViewHolder> {
    private List<Info> basicInfoList = new ArrayList<>();
    private Context context;

    public BasicInformationAdapter(Context context, List<Info> basicInfoList) {
        this.context = context;
        basicInfoList.sort(Comparator.comparing(Info::getOrder));
        this.basicInfoList = basicInfoList;
    }

    public BasicInformationAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_basic_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Info info = basicInfoList.get(position);
        holder.descriptionTextView.setText(info.getDescription());
        holder.titleTextView.setText(info.getTitle());
    }


    public void setItems(List<Info> infoList) {
        this.basicInfoList = infoList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return basicInfoList.size();
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
