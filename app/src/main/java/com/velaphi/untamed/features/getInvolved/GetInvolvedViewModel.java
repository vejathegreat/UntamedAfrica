package com.velaphi.untamed.features.getInvolved;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.GetInvolvedRepository;

import java.util.List;

import javax.inject.Inject;

public class GetInvolvedViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    @Inject
    GetInvolvedRepository getInvolvedRepository;

    private MutableLiveData<List<FoundationModel>> getInvolvedMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Exception> exceptionStatus = new MutableLiveData<>();

    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }

    void retrieveListOfLicencesFromFirebase() {

        getInvolvedRepository.getListOfFoundationsFromFirebase(new GetInvolvedRepository.RepositoryCallback() {
            @Override
            public void onSuccess(List<FoundationModel> foundationsList) {
                getInvolvedMutableLiveData.setValue(foundationsList);
            }

            @Override
            public void onError(Exception exception) {
                exceptionStatus.setValue(exception);
            }
        });

    }

    MutableLiveData<List<FoundationModel>> getGetInvolvedMutableLiveData() {
        return getInvolvedMutableLiveData;
    }

    MutableLiveData<Exception> getExceptionMessage() {
        return exceptionStatus;
    }

}
