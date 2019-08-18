package com.velaphi.untamed.repository.contracts;

import com.velaphi.untamed.features.getInvolved.FoundationModel;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface GetInvolvedRepository {

    void getListOfFoundationsFromFirebase(@NonNull GetInvolvedRepository.RepositoryCallback callback);

    interface RepositoryCallback {

        void onSuccess(List<FoundationModel> foundationsList);

        void onError(Exception exception);
    }
}
