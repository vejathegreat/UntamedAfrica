package com.velaphi.untamed.injection;


import com.velaphi.untamed.features.animalList.AnimalListViewModel;
import com.velaphi.untamed.features.categories.CategoriesViewModel;
import com.velaphi.untamed.features.main.NavigationDrawerViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UntamedAfricaModule.class})
public interface UntamedAfricaComponent {

    void inject(CategoriesViewModel categoriesViewModel);
    void inject(NavigationDrawerViewModel navigationDrawerViewModel);

    void inject(AnimalListViewModel animalListViewModel);

    interface Injectable {
        void inject(UntamedAfricaComponent untamedAfricaComponent);
    }
}
