package com.velaphi.untamed.features.licenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;

import java.util.ArrayList;
import java.util.List;

public class LicensesAdapter extends RecyclerView.Adapter<LicensesAdapter.ViewHolder> {
    private List<LicenceModel> licenceModelList = new ArrayList<>();

    public LicensesAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_license, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        LicenceModel licenceModel = licenceModelList.get(position);
        holder.thirdPartyLibraryNameTextView.setText(licenceModel.getName());
        holder.itemView.setOnClickListener(v -> {

            Context context = holder.itemView.getContext();
            WebView view = (WebView) LayoutInflater.from(context).inflate(R.layout.dialog_licenses, null);
            view.loadUrl(licenceModel.getUrl());
            new AlertDialog.Builder(context, R.style.AppDialog)
                    .setTitle(licenceModel.getName())
                    .setView(view)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        });
    }


    void setItems(List<LicenceModel> licenceModelList) {
        this.licenceModelList = licenceModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return licenceModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView thirdPartyLibraryNameTextView;

        ViewHolder(View view) {
            super(view);
            thirdPartyLibraryNameTextView = view.findViewById(R.id.library_name_textView);
        }
    }
}
