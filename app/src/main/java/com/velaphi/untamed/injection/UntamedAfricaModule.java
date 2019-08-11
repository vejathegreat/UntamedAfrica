package com.velaphi.untamed.injection;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.repository.contracts.AnimalListRepository;
import com.velaphi.untamed.repository.contracts.CategoryRepository;
import com.velaphi.untamed.repository.contracts.LicensesRepository;
import com.velaphi.untamed.repository.implementation.AnimalListRepositoryImpl;
import com.velaphi.untamed.repository.implementation.CategoryRepositoryImpl;
import com.velaphi.untamed.repository.implementation.LicensesRepositoryImpl;
import com.velaphi.untamed.utils.Analytics;
import com.velaphi.untamed.utils.UntamedFirebaseAnalytics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UntamedAfricaModule {

    private UntamedAfricaApp application;

    public UntamedAfricaModule(UntamedAfricaApp application) {
        this.application = application;
    }

    @Provides
    public Context applicationContext() {
        return application;
    }

    @Provides
    @Singleton
    CategoryRepository provideAboutRepository(FirebaseFirestore firebaseFirestore) {
        return new CategoryRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    AnimalListRepository provideAnimalListRepository(FirebaseFirestore firebaseFirestore) {
        return new AnimalListRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    LicensesRepository provideLicensesRepository(FirebaseFirestore firebaseFirestore) {
        return new LicensesRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    FirebaseAnalytics providesFirebaseAnalytics(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    @Singleton
    Analytics providesAnalytics(FirebaseAnalytics firebaseAnalytics) {
        return new UntamedFirebaseAnalytics(firebaseAnalytics);
    }

    @Provides
    @Singleton
    FirebaseFirestore providesFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }
//
//    @Provides
//    @Singleton
//    UntamedDatabase providesUntamedDatabase(Context context){
//        return Room.databaseBuilder(context.getApplicationContext(), UntamedDatabase.class, "untamed.db")
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    FavoritesRepository providesFavoritesRepository(AnimalDao animalDao) {
//        return new FavoritesRepositoryImpl(animalDao);
//    }
}
