package com.velaphi.untamed.features.about;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.AboutUsRepository;

import java.util.List;

import javax.inject.Inject;

public class AboutUsViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    @Inject
    AboutUsRepository aboutUsRepository;

    private MutableLiveData<List<AboutModel>> aboutUsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Exception> exceptionStatus = new MutableLiveData<>();

    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }


    void retrieveAboutUsListFromFirebase() {

        aboutUsRepository.getAboutUsListFromFirebase(new AboutUsRepository.RepositoryCallback() {
            @Override
            public void onSuccess(List<AboutModel> aboutUsList) {
                aboutUsMutableLiveData.setValue(aboutUsList);
            }


            @Override
            public void onError(Exception exception) {
                exceptionStatus.setValue(exception);
            }
        });

    }

    MutableLiveData<List<AboutModel>> getAboutUsMutableLiveData() {
        return aboutUsMutableLiveData;
    }

    MutableLiveData<Exception> getExceptionMessage() {
        return exceptionStatus;
    }
}
