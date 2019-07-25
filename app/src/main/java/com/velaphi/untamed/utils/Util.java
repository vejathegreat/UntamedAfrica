package com.velaphi.untamed.utils;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Util {

    public Util() {
    }

    public StorageReference getImageFromStorage(String path) {

        if (path == null) {
            return null;
        } else {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            return storageRef.child(path);
        }
    }
}
