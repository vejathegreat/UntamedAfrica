package com.velaphi.untamed.features.animalDetails.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.models.Info;

import java.util.List;

public class BasicInformationAdapter extends RecyclerView.Adapter<BasicInformationAdapter.ViewHolder> {
    private List<Info> basicInfoList;
    private Context context;

    public BasicInformationAdapter(Context context, List<Info> basicInfoList) {
        this.context = context;
        this.basicInfoList = basicInfoList;
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
        holder.descriptionTextView.setText(Html.fromHtml(info.getDescription()));
        holder.titleTextView.setText(info.getTitle());
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
