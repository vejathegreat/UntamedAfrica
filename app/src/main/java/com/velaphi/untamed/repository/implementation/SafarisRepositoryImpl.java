package com.velaphi.untamed.repository.implementation;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velaphi.untamed.features.safaries.SafariModel;
import com.velaphi.untamed.repository.contracts.SafarisRepository;

import java.util.ArrayList;
import java.util.List;

public class SafarisRepositoryImpl implements SafarisRepository {


    private final String TAG = "SafarisRepository";
    private FirebaseFirestore firebaseFirestore;

    public SafarisRepositoryImpl(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public void getListOfSafaris(RepositoryCallback callback) {
        firebaseFirestore.collection("safaries")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<SafariModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    SafariModel safariModel = document.toObject(SafariModel.class);
                    list.add(safariModel);
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
