package com.velaphi.untamed.repository.contracts;

import com.velaphi.untamed.features.safaries.SafariModel;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface SafarisRepository {

    void getListOfSafaris(@NonNull SafarisRepository.RepositoryCallback callback);

    interface RepositoryCallback {

        void onSuccess(List<SafariModel> safariModelList);

        void onError(Exception exception);
    }
}
