package com.velaphi.untamed.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import javax.inject.Inject;

public final class CategoryRepository {

    private final FirebaseFirestore firestore;

    @Inject
    public CategoryRepository(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }


    private Query queryCategories() {
        return firestore.collection("categories");
    }


}
