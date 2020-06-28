package com.velaphi.untamed.repository.implementation;

import androidx.lifecycle.LiveData;

import com.velaphi.untamed.features.old_animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.features.database.AnimalsDatabase;
import com.velaphi.untamed.repository.contracts.FavouritesRepository;

import java.util.List;

import javax.inject.Inject;

public class FavouritesRepositoryImpl implements FavouritesRepository {

    @Inject
    AnimalsDatabase animalsDatabase;

    public FavouritesRepositoryImpl(AnimalsDatabase animalsDatabase) {
        this.animalsDatabase = animalsDatabase;
    }

    @Override
    public LiveData<List<AnimalDetailsModel>> getFavouriteAnimals() {
        return animalsDatabase.getAnimalsDao().getFavoriteAnimals();
    }

}
