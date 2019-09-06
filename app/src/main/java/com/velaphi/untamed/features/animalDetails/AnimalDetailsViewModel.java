package com.velaphi.untamed.features.animalDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.repository.contracts.AnimalRepository;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AnimalDetailsViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {
    @Inject
    AnimalRepository animalRepository;

    LiveData<AnimalDetailsModel> isFavourite = new MutableLiveData<>();

    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }

    private void addToFavoriteAnimals(AnimalDetailsModel animalDetailsModel) {
        animalRepository.insertAnimalToRoom(animalDetailsModel);
    }


    private void removeFromFavoriteAnimals(AnimalDetailsModel animalDetailsModel) {
        animalRepository.removeFavoriteAnimal(animalDetailsModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete - deleted animal");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("OnError - deleted animal: ", e);
                    }
                });
    }

    void retrieveIsFavorite(String name) {
        isFavourite = animalRepository.isAnimalFavourite(name);
    }

    void toggleFab(AnimalDetailsModel animalDetailsModel) {
        if (isFavourite.getValue() != null) {
            removeFromFavoriteAnimals(animalDetailsModel);
        } else {
            addToFavoriteAnimals(animalDetailsModel);
        }
    }


}
