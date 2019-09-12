package com.velaphi.untamed.features.animalDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter;
import com.velaphi.untamed.features.animalDetails.adapters.VideosAdapter;
import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private AnimalDetailsModel animalDetailsModel;
    public static String ALL_IMAGES = "ALL_IMAGES";

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        animalDetailsModel = getArguments().getParcelable(AnimalDetailsActivity.EXTRA_ANIMAL_DETAILS);
        setupVideosRecyclerView(view);
        setupImagesRecyclerView(view);
        return view;
    }


    private void setupVideosRecyclerView(View view) {
        RecyclerView videosRecyclerView = view.findViewById(R.id.videos_recyclerView);
        Button viewMoreVideosButton = view.findViewById(R.id.view_more_videos);
        if (animalDetailsModel.getVideoList().size() > 3) {
            viewMoreVideosButton.setVisibility(View.VISIBLE);
            viewMoreVideosButton.setOnClickListener(v -> {
                Intent viewAllVideosIntent = new Intent(getActivity(), AllImagesActivity.class);
                viewAllVideosIntent.putParcelableArrayListExtra(ALL_IMAGES, animalDetailsModel.getVideoList());
                startActivity(viewAllVideosIntent);
            });
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        VideosAdapter videosAdapter = new VideosAdapter(getActivity(), animalDetailsModel.getVideoList(), true);
        videosRecyclerView.setLayoutManager(layoutManager);
        videosRecyclerView.setNestedScrollingEnabled(false);
        videosRecyclerView.setItemAnimator(new DefaultItemAnimator());
        videosRecyclerView.addItemDecoration(new DividerItemDecoration(videosRecyclerView.getContext(),
                layoutManager.getOrientation()));
        videosRecyclerView.setAdapter(videosAdapter);
    }

    private void setupImagesRecyclerView(View view) {
        RecyclerView imagesRecyclerView = view.findViewById(R.id.images_recyclerView);
        Button viewMoreImagesButton = view.findViewById(R.id.view_more_images);
        if (animalDetailsModel.getImageList().size() > 6) {
            viewMoreImagesButton.setVisibility(View.VISIBLE);
            viewMoreImagesButton.setOnClickListener(v -> {
                Intent viewAllImagesIntent = new Intent(getActivity(), AllImagesActivity.class);
                viewAllImagesIntent.putExtra(ALL_IMAGES, (ArrayList<String>) animalDetailsModel.getImageList());
                startActivity(viewAllImagesIntent);
            });
        }
        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        ImagesAdapter imagesAdapter = new ImagesAdapter(getActivity(), animalDetailsModel.getImageList(), true);
        imagesRecyclerView.setLayoutManager(layoutManager);
        imagesRecyclerView.setNestedScrollingEnabled(false);
        imagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        imagesRecyclerView.setAdapter(imagesAdapter);
    }

}
