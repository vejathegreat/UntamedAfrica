package com.velaphi.untamed.repository.contracts;

import androidx.lifecycle.LiveData;

import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;

import java.util.List;

public interface FavouritesRepository {
    LiveData<List<AnimalDetailsModel>> getFavouriteAnimals();
}
