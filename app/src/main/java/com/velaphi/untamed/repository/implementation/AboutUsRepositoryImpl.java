package com.velaphi.untamed.repository.implementation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velaphi.untamed.features.about.AboutModel;
import com.velaphi.untamed.repository.contracts.AboutUsRepository;

import java.util.ArrayList;
import java.util.List;

public class AboutUsRepositoryImpl implements AboutUsRepository {

    private final String TAG = "AboutUsRepository";
    private FirebaseFirestore firebaseFirestore;


    public AboutUsRepositoryImpl(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }


    @Override
    public void getAboutUsListFromFirebase(@NonNull RepositoryCallback callback) {
        firebaseFirestore.collection("about-us")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<AboutModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    AboutModel aboutModel = document.toObject(AboutModel.class);
                    list.add(aboutModel);
                }

                Log.d(TAG, list.toString());

                callback.onSuccess(list);
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
                callback.onError(task.getException());
            }
        });

    }
}
