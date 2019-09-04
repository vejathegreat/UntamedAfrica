package com.velaphi.untamed.features.animalList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.AnimalListRepository;
import com.velaphi.untamed.repository.contracts.FavouritesRepository;

import java.util.List;

import javax.inject.Inject;

public class AnimalListViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    @Inject
    AnimalListRepository animalListRepository;

    @Inject
    FavouritesRepository favouritesRepository;


    private MutableLiveData<List<AnimalDetailsModel>> animalDetailListLiveData = new MutableLiveData<>();
    private LiveData<List<AnimalDetailsModel>> favAnimalsListLiveData = new MutableLiveData<>();
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

    public MutableLiveData<List<AnimalDetailsModel>> getAnimalDetailListLiveData() {
        return animalDetailListLiveData;
    }

    public LiveData<List<AnimalDetailsModel>> retrieveFavMovies() {
        return favouritesRepository.getFavouriteAnimals();
    }

    MutableLiveData<Exception> getExceptionMessage() {
        return exceptionStatus;
    }
}
