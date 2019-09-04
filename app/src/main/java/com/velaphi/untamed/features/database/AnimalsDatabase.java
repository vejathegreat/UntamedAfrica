package com.velaphi.untamed.features.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;

@Database(entities = {AnimalDetailsModel.class}, version = 1, exportSchema = false)
public abstract class AnimalsDatabase extends RoomDatabase {
    public abstract AnimalsDao getAnimalsDao();
}



