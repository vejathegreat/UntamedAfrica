package com.velaphi.untamed.features.licenses;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.LicensesRepository;

import java.util.List;

import javax.inject.Inject;

public class LicencesViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    @Inject
    LicensesRepository licensesRepository;

    private MutableLiveData<List<LicenceModel>> licenceMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Exception> exceptionStatus = new MutableLiveData<>();

    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }

    void retrieveListOfLicencesFromFirebase() {

        licensesRepository.getListOfLicenses(new LicensesRepository.RepositoryCallback() {
            @Override
            public void onSuccess(List<LicenceModel> licenceModelList) {
                licenceMutableLiveData.setValue(licenceModelList);
            }


            @Override
            public void onError(Exception exception) {
                exceptionStatus.setValue(exception);
            }
        });

    }

    MutableLiveData<List<LicenceModel>> getLicenceListLiveData() {
        return licenceMutableLiveData;
    }

    MutableLiveData<Exception> getExceptionMessage() {
        return exceptionStatus;
    }

}
