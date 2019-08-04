package com.velaphi.untamed.features.animalDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;

public class GalleryFragment extends Fragment {

    private AnimalDetailsModel animalDetailsModel;

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        animalDetailsModel = getArguments().getParcelable(AnimalDetailsActivity.EXTRA_ANIMAL_DETAILS);
        Toast.makeText(getActivity(), "Gallery", Toast.LENGTH_SHORT).show();
        return view;
    }
}
