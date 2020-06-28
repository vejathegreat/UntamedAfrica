package com.velaphi.untamed.repository.implementation;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.velaphi.untamed.features.old_animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.repository.contracts.AnimalListRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AnimalListRepositoryImpl implements AnimalListRepository {

    private final String TAG = "AnimalListRepository";

    @Inject
    FirebaseFirestore firebaseFirestore;


    public AnimalListRepositoryImpl(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }


    @Override
    public void getListOfAnimalsFromFirebase(int level, RepositoryCallback callback) {
        Query animals = firebaseFirestore.collection("animals");

        if (level > 0) {
            animals = animals.whereEqualTo("level", level);
        }

        animals.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<AnimalDetailsModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    AnimalDetailsModel animalDetails = document.toObject(AnimalDetailsModel.class);
                    list.add(animalDetails);
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
