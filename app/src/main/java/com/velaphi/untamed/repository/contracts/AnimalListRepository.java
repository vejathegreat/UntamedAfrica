package com.velaphi.untamed.repository.contracts;

import com.velaphi.untamed.features.animalList.models.AnimalDetailsModel;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface AnimalListRepository {

    void getListOfAnimalsFromFirebase(int level, @NonNull RepositoryCallback callback);

    interface RepositoryCallback {

        void onSuccess(List<AnimalDetailsModel> animalDetailsModelList);

        void onError(Exception exception);
    }
}
