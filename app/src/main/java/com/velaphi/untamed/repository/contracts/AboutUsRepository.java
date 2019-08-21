package com.velaphi.untamed.repository.contracts;

import androidx.annotation.NonNull;

import com.velaphi.untamed.features.about.AboutModel;

import java.util.List;

public interface AboutUsRepository {

    void getAboutUsListFromFirebase(@NonNull RepositoryCallback callback);

    interface RepositoryCallback {

        void onSuccess(List<AboutModel> aboutUsList);

        void onError(Exception exception);
    }
}
