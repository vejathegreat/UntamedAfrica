package com.velaphi.untamed.features.old_animalDetails.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.old_animalDetails.models.Challenge;

import java.util.List;

public class ChallangesAdapter extends RecyclerView.Adapter<ChallangesAdapter.ViewHolder> {
    private List<Challenge> challengeList;
    private Context context;

    public ChallangesAdapter(Context context, List<Challenge> challengeList) {
        this.context = context;
        this.challengeList = challengeList;
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
        Challenge challenge = challengeList.get(position);
        holder.titleTextView.setText(challenge.getTitle());
        holder.detailsTextView.setText(challenge.getDetails());
    }


    @Override
    public int getItemCount() {
        return challengeList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView detailsTextView;

        ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.title_textView);
            detailsTextView = view.findViewById(R.id.description_textView);
        }
    }
}

