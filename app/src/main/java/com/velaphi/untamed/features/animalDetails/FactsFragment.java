package com.velaphi.untamed.features.animalDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.adapters.BasicInformationAdapter;
import com.velaphi.untamed.features.animalDetails.adapters.CountriesAdapter;
import com.velaphi.untamed.features.animalDetails.adapters.FactsAdapter;
import com.velaphi.untamed.features.animalDetails.adapters.PredatorsAdapter;
import com.velaphi.untamed.features.animalDetails.adapters.PreyAdapter;
import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.utils.CirclePagerIndicatorDecoration;
import com.velaphi.untamed.utils.StartSnapHelper;

public class FactsFragment extends Fragment {

    private AnimalDetailsModel animalDetailsModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facts, container, false);
        animalDetailsModel = getArguments().getParcelable(AnimalDetailsActivity.EXTRA_ANIMAL_DETAILS);
        setPredators(view);
        setPrey(view);
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
        factsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(factsRecyclerView);
        factsRecyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        if (animalDetailsModel.getFacts() != null && !animalDetailsModel.getFacts().isEmpty()) {
            factsAdapter.setItems(animalDetailsModel.getFacts());
        }

    }


    private void setPredators(View view) {
        RecyclerView predatorRecyclerView = view.findViewById(R.id.predators_recyclerView);
        LinearLayout predatorLinearLayout = view.findViewById(R.id.predator_linear_layout);

        PredatorsAdapter predatorsAdapter = new PredatorsAdapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        predatorRecyclerView.setLayoutManager(layoutManager);
        predatorRecyclerView.setNestedScrollingEnabled(false);
        predatorRecyclerView.setItemAnimator(new DefaultItemAnimator());
        predatorRecyclerView.setAdapter(predatorsAdapter);

        if (animalDetailsModel.getPredators() != null && !animalDetailsModel.getPredators().isEmpty()) {
            predatorsAdapter.setItems(animalDetailsModel.getPredators());
            predatorLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setPrey(View view) {
        RecyclerView preyRecyclerView = view.findViewById(R.id.prey_recyclerView);
        LinearLayout preyLinearLayout = view.findViewById(R.id.prey_linear_layout);
        PreyAdapter preyAdapter = new PreyAdapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        preyRecyclerView.setLayoutManager(layoutManager);
        preyRecyclerView.setNestedScrollingEnabled(false);
        preyRecyclerView.setItemAnimator(new DefaultItemAnimator());
        preyRecyclerView.setAdapter(preyAdapter);

        if (animalDetailsModel.getPrey() != null && !animalDetailsModel.getPrey().isEmpty()) {
            preyLinearLayout.setVisibility(View.VISIBLE);
            preyAdapter.setItems(animalDetailsModel.getPrey());
        }
    }

    private void setLocations(View view) {
        String name = getString(R.string.locations, animalDetailsModel.getName());
        RecyclerView countriesRecyclerView = view.findViewById(R.id.countries_recyclerView);
        TextView locationHeaderTextView = view.findViewById(R.id.habitat_header_textview);
        locationHeaderTextView.setText(name);
        if(animalDetailsModel.getLocated() != null && !animalDetailsModel.getLocated().isEmpty()){

            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
            CountriesAdapter countriesAdapter = new CountriesAdapter(animalDetailsModel.getLocated());
            countriesRecyclerView.setLayoutManager(layoutManager);
            countriesRecyclerView.setNestedScrollingEnabled(false);
            countriesRecyclerView.setItemAnimator(new DefaultItemAnimator());
            countriesRecyclerView.setAdapter(countriesAdapter);
        }

    }
}