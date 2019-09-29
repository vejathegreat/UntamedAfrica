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

import com.google.android.material.card.MaterialCardView;
import com.velaphi.untamed.R;
import com.velaphi.untamed.features.animalDetails.adapters.ImagesAdapter;
import com.velaphi.untamed.features.animalDetails.adapters.VideosAdapter;
import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;

import java.util.ArrayList;

import static com.velaphi.untamed.features.animalDetails.AllImagesActivity.ALL_IMAGES;
import static com.velaphi.untamed.features.animalDetails.AllVideosActivity.ALL_VIDEOS;

public class GalleryFragment extends Fragment {

    private AnimalDetailsModel animalDetailsModel;
    private static final int MINIMUM_IMAGES = 6;
    private static final int MINIMUM_VIDEOS = 3;

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
        MaterialCardView videosMaterialCardView = view.findViewById(R.id.videos_card_view);

        if (animalDetailsModel.getVideoList() != null && !animalDetailsModel.getVideoList().isEmpty()) {
            videosMaterialCardView.setVisibility(View.VISIBLE);
            if (animalDetailsModel.getVideoList().size() > MINIMUM_VIDEOS) {
                viewMoreVideosButton.setVisibility(View.VISIBLE);
                viewMoreVideosButton.setOnClickListener(v -> {
                    Intent viewAllVideos = new Intent(getActivity(), AllVideosActivity.class);
                    viewAllVideos.putParcelableArrayListExtra(ALL_VIDEOS, animalDetailsModel.getVideoList());
                    startActivity(viewAllVideos);
                });
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            VideosAdapter videosAdapter = new VideosAdapter(getActivity(), true);
            videosRecyclerView.setLayoutManager(layoutManager);
            videosRecyclerView.setNestedScrollingEnabled(false);
            videosRecyclerView.setItemAnimator(new DefaultItemAnimator());
            videosRecyclerView.addItemDecoration(new DividerItemDecoration(videosRecyclerView.getContext(),
                    layoutManager.getOrientation()));
            videosRecyclerView.setAdapter(videosAdapter);
            videosAdapter.setItems(animalDetailsModel.getVideoList());
        }
    }

    private void setupImagesRecyclerView(View view) {
        RecyclerView imagesRecyclerView = view.findViewById(R.id.images_recyclerView);
        Button viewMoreImagesButton = view.findViewById(R.id.view_more_images);
        if (animalDetailsModel.getImageList() != null && !animalDetailsModel.getImageList().isEmpty()) {
            if (animalDetailsModel.getImageList().size() > MINIMUM_IMAGES) {
                viewMoreImagesButton.setVisibility(View.VISIBLE);
                viewMoreImagesButton.setOnClickListener(v -> {
                    Intent viewAllImagesIntent = new Intent(getActivity(), AllImagesActivity.class);
                    viewAllImagesIntent.putStringArrayListExtra(ALL_IMAGES, (ArrayList<String>) animalDetailsModel.getImageList());
                    startActivity(viewAllImagesIntent);
                });
            }
            LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
            ImagesAdapter imagesAdapter = new ImagesAdapter(getActivity(), true);
            imagesRecyclerView.setLayoutManager(layoutManager);
            imagesRecyclerView.setNestedScrollingEnabled(false);
            imagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
            imagesRecyclerView.setAdapter(imagesAdapter);
            imagesAdapter.setItems(animalDetailsModel.getImageList());
        }
    }

}
