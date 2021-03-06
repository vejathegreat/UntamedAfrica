package com.velaphi.untamed.injection;


import com.velaphi.untamed.features.about.AboutUsViewModel;
import com.velaphi.untamed.features.animalDetails.AnimalDetailsViewModel;
import com.velaphi.untamed.features.animalList.AnimalListViewModel;
import com.velaphi.untamed.features.categories.CategoriesViewModel;
import com.velaphi.untamed.features.getInvolved.GetInvolvedViewModel;
import com.velaphi.untamed.features.licenses.LicencesViewModel;
import com.velaphi.untamed.features.main.NavigationDrawerViewModel;
import com.velaphi.untamed.features.safaries.SafarisViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UntamedAfricaModule.class})
public interface UntamedAfricaComponent {

    void inject(CategoriesViewModel categoriesViewModel);
    void inject(NavigationDrawerViewModel navigationDrawerViewModel);
    void inject(AnimalListViewModel animalListViewModel);
    void inject(LicencesViewModel licencesViewModel);
    void inject(GetInvolvedViewModel getInvolvedViewModel);
    void inject(SafarisViewModel safarisViewModel);
    void inject(AboutUsViewModel aboutUsViewModel);

    void inject(AnimalDetailsViewModel animalDetailsViewModel);

    interface Injectable {
        void inject(UntamedAfricaComponent untamedAfricaComponent);
    }
}
