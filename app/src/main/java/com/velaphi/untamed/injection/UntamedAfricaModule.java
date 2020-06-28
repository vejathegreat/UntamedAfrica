package com.velaphi.untamed.injection;

import android.content.Context;

import androidx.room.Room;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.features.database.AnimalsDao;
import com.velaphi.untamed.features.database.AnimalsDatabase;
import com.velaphi.untamed.repository.contracts.AboutUsRepository;
import com.velaphi.untamed.repository.contracts.AllAnimalsRepository;
import com.velaphi.untamed.repository.contracts.AnimalListRepository;
import com.velaphi.untamed.repository.contracts.AnimalRepository;
import com.velaphi.untamed.repository.contracts.CategoryRepository;
import com.velaphi.untamed.repository.contracts.FavouritesRepository;
import com.velaphi.untamed.repository.contracts.FilterRepository;
import com.velaphi.untamed.repository.contracts.GetInvolvedRepository;
import com.velaphi.untamed.repository.contracts.HomeRepository;
import com.velaphi.untamed.repository.contracts.LicensesRepository;
import com.velaphi.untamed.repository.contracts.SafarisRepository;
import com.velaphi.untamed.repository.implementation.AboutUsRepositoryImpl;
import com.velaphi.untamed.repository.implementation.AllAnimalsRepositoryImpl;
import com.velaphi.untamed.repository.implementation.AnimalListRepositoryImpl;
import com.velaphi.untamed.repository.implementation.AnimalRepositoryImpl;
import com.velaphi.untamed.repository.implementation.CategoryRepositoryImpl;
import com.velaphi.untamed.repository.implementation.FavouritesRepositoryImpl;
import com.velaphi.untamed.repository.implementation.FilterRepositoryImpl;
import com.velaphi.untamed.repository.implementation.GetInvolvedRepositoryImpl;
import com.velaphi.untamed.repository.implementation.HomeRepositoryImpl;
import com.velaphi.untamed.repository.implementation.LicensesRepositoryImpl;
import com.velaphi.untamed.repository.implementation.SafarisRepositoryImpl;
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
    FavouritesRepository provideFavouritesRepository(AnimalsDatabase animalsDatabase) {
        return new FavouritesRepositoryImpl(animalsDatabase);
    }

    @Provides
    @Singleton
    LicensesRepository provideLicensesRepository(FirebaseFirestore firebaseFirestore) {
        return new LicensesRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    SafarisRepository provideSafarisRepository(FirebaseFirestore firebaseFirestore) {
        return new SafarisRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    GetInvolvedRepository provideGetInvolvedRepository(FirebaseFirestore firebaseFirestore) {
        return new GetInvolvedRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    AboutUsRepository provideAboutUsRepository(FirebaseFirestore firebaseFirestore) {
        return new AboutUsRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    AnimalRepository provideAnimalRepository(AnimalsDatabase animalsDatabase) {
        return new AnimalRepositoryImpl(animalsDatabase);
    }

    @Provides
    @Singleton
    FilterRepository provideFilterRepository(FirebaseFirestore firebaseFirestore) {
        return new FilterRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    AllAnimalsRepository provideAllAnimalsRepository(FirebaseFirestore firebaseFirestore) {
        return new AllAnimalsRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    HomeRepository provideHomeRepository(FirebaseFirestore firebaseFirestore) {
        return new HomeRepositoryImpl(firebaseFirestore);
    }

    @Provides
    @Singleton
    AnimalsDao provideAnimalsDao(AnimalsDatabase animalsDatabase) {
        return animalsDatabase.getAnimalsDao();
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

    @Provides
    @Singleton
    AnimalsDatabase providesAnimalsDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AnimalsDatabase.class, "favorite_animals.db").build();
    }
}
