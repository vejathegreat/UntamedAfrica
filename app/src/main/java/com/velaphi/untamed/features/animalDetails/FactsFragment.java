package com.velaphi.untamed.features.animalDetails;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.adapters.BasicInformationAdapter;
import com.velaphi.untamed.features.animalDetails.adapters.FactsAdapter;
import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.utils.CirclePagerIndicatorDecoration;

public class FactsFragment extends Fragment {

    private AnimalDetailsModel animalDetailsModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facts, container, false);
        animalDetailsModel = getArguments().getParcelable(AnimalDetailsActivity.EXTRA_ANIMAL_DETAILS);
        setPredators(view);
        setLocations(view);
        setFacts(view);
        setBasicInfo(view);
        return view;
    }

    private void setBasicInfo(View view) {
        RecyclerView basicInfoRecyclerView = view.findViewById(R.id.basic_info_recyclerview);
        BasicInformationAdapter basicInformationAdapter = new BasicInformationAdapter(getActivity(), animalDetailsModel.getBasicInfo());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        basicInfoRecyclerView.setLayoutManager(layoutManager);
        basicInfoRecyclerView.setNestedScrollingEnabled(false);
        basicInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        basicInfoRecyclerView.setAdapter(basicInformationAdapter);
    }


    private void setFacts(View view) {
        RecyclerView factsRecyclerView = view.findViewById(R.id.facts_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        FactsAdapter factsAdapter = new FactsAdapter(getActivity());
        factsRecyclerView.setLayoutManager(layoutManager);
        factsRecyclerView.setNestedScrollingEnabled(false);
        factsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        factsRecyclerView.setAdapter(factsAdapter);
        factsRecyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        factsAdapter.setItems(animalDetailsModel.getFacts());
    }


    private void setPredators(View view) {
        final ChipGroup chipGroup = view.findViewById(R.id.predators_group);
        for (String predator : animalDetailsModel.getPredators()) {
            addChip(predator, chipGroup);
        }
    }

    private void addChip(String value, ChipGroup chipGroup) {
        final Chip chip = new Chip(getActivity());
        int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8,
                getResources().getDisplayMetrics());

        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(value);
        chip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.transparent)));
        chip.setChipStrokeColorResource(R.color.colorPrimaryDark);
        chip.setChipStrokeWidth(1);
        chipGroup.addView(chip);

    }

    private void setLocations(View view) {
        final ChipGroup chipGroup = view.findViewById(R.id.locations_group);
        for (String location : animalDetailsModel.getLocated()) {
            addChip(location, chipGroup);
        }
    }

}