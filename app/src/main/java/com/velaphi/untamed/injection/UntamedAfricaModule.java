package com.velaphi.untamed.injection;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velaphi.untamed.UntamedAfricaApp;
import com.velaphi.untamed.repository.contracts.CategoryRepository;
import com.velaphi.untamed.repository.implementation.CategoryRepositoryImpl;
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
}
