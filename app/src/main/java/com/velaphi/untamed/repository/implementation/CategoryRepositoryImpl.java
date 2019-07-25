package com.velaphi.untamed.repository.implementation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.velaphi.untamed.features.categories.CategoryModel;
import com.velaphi.untamed.repository.contracts.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private final String TAG = "CategoryRepository";
    private FirebaseFirestore firebaseFirestore;

    public CategoryRepositoryImpl(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public void getListOfCategories(final RepositoryCallback callback) {
        firebaseFirestore.collection("categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<CategoryModel> list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        CategoryModel category = document.toObject(CategoryModel.class);
                        list.add(category);
                    }

                    Log.d(TAG, list.toString());

                    callback.onSuccess(list);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                    callback.onError(task.getException());
                }
            }
        });
    }
}
