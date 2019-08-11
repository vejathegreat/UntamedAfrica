//package com.velaphi.untamed.features.favorites.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//
//import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
//
//import java.util.List;
//
//import io.reactivex.Flowable;
//
//
//@Dao
//public interface AnimalDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAnimals(AnimalDetailsModel animalDetailsModel);
//
//    @Query("delete from favourite_animals where id = name")
//    int removeAnimals(String name);
//
//    @Query("SELECT * from favourite_animals")
//    Flowable<List<AnimalDetailsModel>> getFavoriteAnimals();
//
//
//    @Query("select isFavourite from favourite_animals where id = name")
//    LiveData<Boolean> isAnimalFavorite(String name);
//
//}
