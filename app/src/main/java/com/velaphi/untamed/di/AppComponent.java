package com.velaphi.untamed.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ViewModelModule.class,
        FirebaseModule.class,
        ActivitiesModule.class,
})
interface AppComponent extends AndroidInjector<DaggerApplication> {

}
