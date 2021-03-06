package com.velaphi.untamed.utils;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AppUtil {

    public AppUtil() {
    }

    public static StorageReference getImageFromStorage(String path) {

        if (path == null) {
            return null;
        } else {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            return storageRef.child(path);
        }
    }

    public static StorageReference getVideoFromStorage(String path) {

        if (path == null) {
            return null;
        } else {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            return storageRef.child(path);
        }
    }

}
