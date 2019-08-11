package com.velaphi.untamed.features.safaries;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.SafarisRepository;

import java.util.List;

import javax.inject.Inject;

public class SafarisViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    @Inject
    SafarisRepository safarisRepository;

    private MutableLiveData<List<SafariModel>> safarisListLiveData = new MutableLiveData<>();
    private MutableLiveData<Exception> exceptionStatus = new MutableLiveData<>();


    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }

    void retrieveListOfSafarisFromFirebase() {
        safarisRepository.getListOfSafaris(new SafarisRepository.RepositoryCallback() {
            @Override
            public void onSuccess(List<SafariModel> safariModelList) {
                safarisListLiveData.setValue(safariModelList);
            }

            @Override
            public void onError(Exception exception) {
                exceptionStatus.setValue(exception);

            }
        });
    }

    MutableLiveData<List<SafariModel>> getSafarisListLiveData() {
        return safarisListLiveData;
    }

    MutableLiveData<Exception> getExceptionMessage() {
        return exceptionStatus;
    }

}
