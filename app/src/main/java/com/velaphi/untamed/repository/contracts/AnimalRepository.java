package com.velaphi.untamed.repository.contracts;

import androidx.lifecycle.LiveData;

import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;

import io.reactivex.Completable;

public interface AnimalRepository {

    Completable insertFavoriteAnimal(AnimalDetailsModel animalDetailsModel);

    Completable removeFavoriteAnimal(AnimalDetailsModel animalDetailsModel);

    LiveData<AnimalDetailsModel> isAnimalFavourite(String name);
}
