package com.velaphi.untamed.repository.implementation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velaphi.untamed.features.licenses.LicenceModel;
import com.velaphi.untamed.repository.contracts.LicensesRepository;

import java.util.ArrayList;
import java.util.List;

public class LicensesRepositoryImpl implements LicensesRepository {

    private final String TAG = "LicensesRepository";
    private FirebaseFirestore firebaseFirestore;

    public LicensesRepositoryImpl(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public void getListOfLicenses(@NonNull RepositoryCallback callback) {

        firebaseFirestore.collection("open_source")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<LicenceModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    LicenceModel licenceModel = document.toObject(LicenceModel.class);
                    list.add(licenceModel);
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
