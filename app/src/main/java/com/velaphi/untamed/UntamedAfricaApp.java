package com.velaphi.untamed;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import com.velaphi.untamed.injection.DaggerUntamedAfricaComponent;
import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.injection.UntamedAfricaModule;

public class UntamedAfricaApp extends MultiDexApplication {

    private UntamedAfricaComponent untamedAfricaComponent = createUntamedAfricaComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        MultiDex.install(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true);
    }

    UntamedAfricaComponent createUntamedAfricaComponent() {
        return DaggerUntamedAfricaComponent.builder()
                .untamedAfricaModule(new UntamedAfricaModule(this))
                .build();
    }

    public UntamedAfricaComponent getUntamedAfricaComponent() {
        return untamedAfricaComponent;
    }
}
