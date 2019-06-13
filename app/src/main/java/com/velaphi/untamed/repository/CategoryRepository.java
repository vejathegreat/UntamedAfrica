package com.velaphi.untamed.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

public final class CategoryRepository {

    private final FirebaseFirestore firestore;

    @Inject
    public CategoryRepository(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }


}
