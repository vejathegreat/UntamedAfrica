package com.velaphi.untamed.injection

import com.velaphi.untamed.features.about.AboutUsViewModel
import com.velaphi.untamed.features.all_animals.AnimalsViewModel
import com.velaphi.untamed.features.old_animalDetails.AnimalDetailsViewModel
import com.velaphi.untamed.features.animalList.AnimalListViewModel
import com.velaphi.untamed.features.categories.CategoriesViewModel
import com.velaphi.untamed.features.getInvolved.GetInvolvedViewModel
import com.velaphi.untamed.features.home.HomeViewModel
import com.velaphi.untamed.features.licenses.LicencesViewModel
import com.velaphi.untamed.features.main.NavigationDrawerViewModel
import com.velaphi.untamed.features.safaries.SafarisViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UntamedAfricaModule::class])
interface UntamedAfricaComponent {
    fun inject(categoriesViewModel: CategoriesViewModel?)
    fun inject(navigationDrawerViewModel: NavigationDrawerViewModel?)
    fun inject(animalListViewModel: AnimalListViewModel?)
    fun inject(animalsViewModel: AnimalsViewModel?)
    fun inject(licencesViewModel: LicencesViewModel?)
    fun inject(homeViewModel: HomeViewModel?)
    fun inject(getInvolvedViewModel: GetInvolvedViewModel?)
    fun inject(safarisViewModel: SafarisViewModel?)
    fun inject(aboutUsViewModel: AboutUsViewModel?)
    fun inject(animalDetailsViewModel: AnimalDetailsViewModel?)

    interface Injectable {
        fun inject(untamedAfricaComponent: UntamedAfricaComponent?)
    }
}