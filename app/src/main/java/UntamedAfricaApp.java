import com.velaphi.untamed.BuildConfig;
import com.velaphi.untamed.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public final class UntamedAfricaApp extends DaggerApplication {

    private static UntamedAfricaApp instance;
    
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static synchronized UntamedAfricaApp getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }
}
