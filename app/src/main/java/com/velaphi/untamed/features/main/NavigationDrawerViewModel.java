package com.velaphi.untamed.features.main;

import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.utils.Analytics;
import com.velaphi.untamed.utils.SingleLiveEvent;

import javax.inject.Inject;

public class NavigationDrawerViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    SingleLiveEvent<Void> categoryListScreenTrigger = new SingleLiveEvent<>();
    SingleLiveEvent<Void> licenceListScreenTrigger = new SingleLiveEvent<>();
    SingleLiveEvent<Void> safarisScreenTrigger = new SingleLiveEvent<>();
    SingleLiveEvent<Void> getInvolvedScreenTrigger = new SingleLiveEvent<>();
    SingleLiveEvent<Void> aboutUsScreenTrigger = new SingleLiveEvent<>();
    SingleLiveEvent<Void> favoritesScreenTrigger = new SingleLiveEvent<>();

    @Inject
    Analytics analytics;

    @Override
    public void inject(UntamedAfricaComponent untamedAfricaComponent) {
        untamedAfricaComponent.inject(this);
    }

    public void openCategories() {
        analytics.trackOpenCategories();
        categoryListScreenTrigger.call();
    }

    public void openLicenses() {
        analytics.trackOpenLicences();
        licenceListScreenTrigger.call();
    }

    public void openSafaris() {
        analytics.trackOpenSafaris();
        safarisScreenTrigger.call();
    }

    public void openGetInvolved() {
        analytics.trackGetInvolved();
        getInvolvedScreenTrigger.call();
    }

    public void openAboutUs() {
        analytics.trackAboutUs();
        aboutUsScreenTrigger.call();
    }

    public void openFavorites() {
        analytics.trackFavorites();
        favoritesScreenTrigger.call();
    }
}
