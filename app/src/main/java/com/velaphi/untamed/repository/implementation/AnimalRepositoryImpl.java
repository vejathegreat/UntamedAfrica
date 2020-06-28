package com.velaphi.untamed.repository.implementation;


import androidx.lifecycle.LiveData;

import com.velaphi.untamed.features.old_animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.features.database.AnimalsDatabase;
import com.velaphi.untamed.repository.contracts.AnimalRepository;

import io.reactivex.Completable;

public class AnimalRepositoryImpl implements AnimalRepository {

    private AnimalsDatabase animalsDatabase;

    public AnimalRepositoryImpl(AnimalsDatabase animalsDatabase) {
        this.animalsDatabase = animalsDatabase;
    }


    @Override
    public Completable insertFavoriteAnimal(AnimalDetailsModel animalDetailsModel) {
        return Completable.fromAction(() -> animalsDatabase.getAnimalsDao().insertFavoriteAnimal(animalDetailsModel));
    }

    @Override
    public Completable removeFavoriteAnimal(AnimalDetailsModel animalDetailsModel) {
        return Completable.fromAction(() -> animalsDatabase.getAnimalsDao().removeFavoriteAnimal(animalDetailsModel));
    }

    @Override
    public LiveData<AnimalDetailsModel> isAnimalFavourite(String name) {
        return animalsDatabase.getAnimalsDao().isAnimalFavourite(name);
    }
}
