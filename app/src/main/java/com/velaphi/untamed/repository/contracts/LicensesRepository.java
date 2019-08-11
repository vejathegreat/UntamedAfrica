package com.velaphi.untamed.repository.contracts;

import androidx.annotation.NonNull;

import com.velaphi.untamed.features.licenses.LicenceModel;

import java.util.List;

public interface LicensesRepository {

    void getListOfLicenses(@NonNull RepositoryCallback callback);

    interface RepositoryCallback {

        void onSuccess(List<LicenceModel> licenceModelList);

        void onError(Exception exception);
    }
}
