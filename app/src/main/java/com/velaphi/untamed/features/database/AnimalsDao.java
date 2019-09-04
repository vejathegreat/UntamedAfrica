package com.velaphi.untamed.features.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;

import java.util.List;

@Dao
public interface AnimalsDao {

    @Query("SELECT * FROM  favorite_animals")
    LiveData<List<AnimalDetailsModel>> getFavoriteAnimals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteAnimal(AnimalDetailsModel animalDetailsModel);

    @Delete
    void removeFavoriteAnimal(AnimalDetailsModel animalDetailsModel);


    @Query("select * from favorite_animals where name = :name")
    LiveData<AnimalDetailsModel> isAnimalFavourite(String name);
}
