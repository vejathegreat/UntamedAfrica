package com.velaphi.untamed.repository.implementation;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
import com.velaphi.untamed.features.database.AnimalsDatabase;
import com.velaphi.untamed.repository.contracts.AnimalRepository;

import io.reactivex.Completable;

public class AnimalRepositoryImpl implements AnimalRepository {

    private AnimalsDatabase animalsDatabase;

    public AnimalRepositoryImpl(AnimalsDatabase animalsDatabase) {
        this.animalsDatabase = animalsDatabase;
    }


    @Override
    public void insertAnimalToRoom(AnimalDetailsModel animalDetailsModel) {
        new insertAsyncTask(animalsDatabase).execute(animalDetailsModel);
    }

    @Override
    public Completable removeFavoriteAnimal(AnimalDetailsModel animalDetailsModel) {
        return Completable.fromAction(() -> animalsDatabase.getAnimalsDao().removeFavoriteAnimal(animalDetailsModel));
    }

    @Override
    public LiveData<AnimalDetailsModel> isAnimalFavourite(String name) {
        return animalsDatabase.getAnimalsDao().isAnimalFavourite(name);
    }

    private static class insertAsyncTask extends AsyncTask<AnimalDetailsModel, Void, Void> {

        AnimalsDatabase animalsDatabase;

        public insertAsyncTask(AnimalsDatabase animalsDatabase) {
            this.animalsDatabase = animalsDatabase;
        }

        @Override
        protected Void doInBackground(AnimalDetailsModel... animalDetailsModels) {
            animalsDatabase.getAnimalsDao().insertFavoriteAnimal(animalDetailsModels[0]);
            return null;
        }
    }
}
