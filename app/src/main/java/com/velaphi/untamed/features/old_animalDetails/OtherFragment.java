package com.velaphi.untamed.features.old_animalDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.old_animalDetails.adapters.ChallangesAdapter;
import com.velaphi.untamed.features.old_animalDetails.models.AnimalDetailsModel;

public class OtherFragment extends Fragment {

    private AnimalDetailsModel animalDetailsModel;

    public OtherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        animalDetailsModel = getArguments().getParcelable(AnimalDetailsActivity.EXTRA_ANIMAL_DETAILS);
        setupChallengesRecyclerView(view);
        return view;
    }

    private void setupChallengesRecyclerView(View view) {
        RecyclerView challengesRecyclerView = view.findViewById(R.id.challenges_recyclerView);
        ChallangesAdapter challangesAdapter = new ChallangesAdapter(getActivity(), animalDetailsModel.getChallenges());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        challengesRecyclerView.setLayoutManager(layoutManager);
        challengesRecyclerView.setNestedScrollingEnabled(false);
        challengesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        challengesRecyclerView.setAdapter(challangesAdapter);
    }
}
