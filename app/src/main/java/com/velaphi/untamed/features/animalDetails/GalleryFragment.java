package com.velaphi.untamed.features.animalDetails;

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

public class GalleryFragment extends Fragment {

    private AnimalDetailsModel animalDetailsModel;

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        animalDetailsModel = getArguments().getParcelable(AnimalDetailsActivity.EXTRA_ANIMAL_DETAILS);
        setupButtons(view);
        setupVideosRecyclerView(view);
        setupImagesRecyclerView(view);
        return view;
    }

    private void setupButtons(View view) {
        Button viewMoreVideosButton = view.findViewById(R.id.view_more_videos);
        Button viewMoreImagesButton = view.findViewById(R.id.view_more_images);

        int MAX_VIDEOS = 5;
        int MAX_IMAGES = 8;

        if (animalDetailsModel.getVideoList().size() <= MAX_VIDEOS) {
            viewMoreVideosButton.setVisibility(View.GONE);
        }

        if (animalDetailsModel.getImageList().size() <= MAX_IMAGES) {
            viewMoreImagesButton.setVisibility(View.GONE);
        }
    }

    private void setupVideosRecyclerView(View view) {
        RecyclerView videosRecyclerView = view.findViewById(R.id.videos_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        VideosAdapter videosAdapter = new VideosAdapter(getActivity(), animalDetailsModel.getVideoList());
        videosRecyclerView.setLayoutManager(layoutManager);
        videosRecyclerView.setNestedScrollingEnabled(false);
        videosRecyclerView.setItemAnimator(new DefaultItemAnimator());
        videosRecyclerView.addItemDecoration(new DividerItemDecoration(videosRecyclerView.getContext(),
                layoutManager.getOrientation()));
        videosRecyclerView.setAdapter(videosAdapter);
    }

    private void setupImagesRecyclerView(View view) {
        RecyclerView imagesRecyclerView = view.findViewById(R.id.images_recyclerView);
        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        ImagesAdapter imagesAdapter = new ImagesAdapter(getActivity(), animalDetailsModel.getImageList());
        imagesRecyclerView.setLayoutManager(layoutManager);
        imagesRecyclerView.setNestedScrollingEnabled(false);
        imagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        imagesRecyclerView.setAdapter(imagesAdapter);
    }

}
