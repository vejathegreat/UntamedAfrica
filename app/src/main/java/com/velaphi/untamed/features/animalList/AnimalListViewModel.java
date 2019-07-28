package com.velaphi.untamed.features.animalList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.features.animalList.models.AnimalDetailsModel;
import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.AnimalListRepository;

import java.util.List;

import javax.inject.Inject;

public class AnimalListViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    @Inject
    AnimalListRepository animalListRepository;

    private MutableLiveData<List<AnimalDetailsModel>> animalDetailListLiveData = new MutableLiveData<>();
    private MutableLiveData<Exception> exceptionStatus = new MutableLiveData<>();


    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }


    void retrieveListOfAnimalsFromFirebase(int level) {
        animalListRepository.getListOfAnimalsFromFirebase(level, new AnimalListRepository.RepositoryCallback() {
            @Override
            public void onSuccess(List<AnimalDetailsModel> animalDetailsModelList) {
                animalDetailListLiveData.setValue(animalDetailsModelList);
            }

            @Override
            public void onError(Exception exception) {
                exceptionStatus.setValue(exception);
            }
        });
    }

    MutableLiveData<List<AnimalDetailsModel>> getAnimalDetailListLiveData() {
        return animalDetailListLiveData;
    }

    MutableLiveData<Exception> getExceptionMessage() {
        return exceptionStatus;
    }
}
