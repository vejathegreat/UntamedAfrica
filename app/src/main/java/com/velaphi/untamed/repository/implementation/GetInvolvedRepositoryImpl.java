package com.velaphi.untamed.repository.implementation;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velaphi.untamed.features.getInvolved.FoundationModel;
import com.velaphi.untamed.repository.contracts.GetInvolvedRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetInvolvedRepositoryImpl implements GetInvolvedRepository {

    private final String TAG = "GetInvolvedRepository";
    private FirebaseFirestore firebaseFirestore;

    public GetInvolvedRepositoryImpl(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public void getListOfFoundationsFromFirebase(RepositoryCallback callback) {

        firebaseFirestore.collection("foundations")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<FoundationModel> list = new ArrayList<>();
                for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    FoundationModel foundationModel = document.toObject(FoundationModel.class);
                    list.add(foundationModel);
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
