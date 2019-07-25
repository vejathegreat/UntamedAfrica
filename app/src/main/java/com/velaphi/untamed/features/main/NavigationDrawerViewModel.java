package com.velaphi.untamed.features.main;

import androidx.lifecycle.ViewModel;

import com.velaphi.untamed.injection.UntamedAfricaComponent;
import com.velaphi.untamed.utils.Analytics;
import com.velaphi.untamed.utils.SingleLiveEvent;

import javax.inject.Inject;

public class NavigationDrawerViewModel extends ViewModel implements UntamedAfricaComponent.Injectable {

    public SingleLiveEvent<Void> categoryListScreenTrigger = new SingleLiveEvent<>();
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
}
