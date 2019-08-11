package com.velaphi.untamed.repository.implementation;
//
//import androidx.lifecycle.LiveData;
//
//import com.velaphi.untamed.features.animalDetails.models.AnimalDetailsModel;
//import com.velaphi.untamed.features.favorites.dao.AnimalDao;
//import com.velaphi.untamed.repository.contracts.FavoritesRepository;
//
//import java.util.List;
//
//import io.reactivex.Completable;
//import io.reactivex.Flowable;
//
//public class FavoritesRepositoryImpl implements FavoritesRepository {
//
//    private AnimalDao animalDao;
//
//    public FavoritesRepositoryImpl(AnimalDao animalDao) {
//        this.animalDao = animalDao;
//    }
//
//    @Override
//    public Flowable<List<AnimalDetailsModel>> getFavouriteAnimals() {
//        return this.animalDao.getFavoriteAnimals();
//    }
//
//    @Override
//    public Completable addFavoriteAnimal(AnimalDetailsModel animalDetailsModel) {
//        return Completable.fromAction(() -> FavoritesRepositoryImpl.this.animalDao.insertAnimals(animalDetailsModel));
//    }
//
//    @Override
//    public Completable removeFavouriteAnimal(String name) {
//        return Completable.fromAction(() -> FavoritesRepositoryImpl.this.animalDao.removeAnimals(name));
//    }
//
//    @Override
//    public LiveData<Boolean> isAnimalFavourite(String name) {
//        return animalDao.isAnimalFavorite(name);
//    }
//}
