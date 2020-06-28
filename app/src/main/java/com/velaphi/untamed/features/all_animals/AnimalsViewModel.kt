package com.velaphi.untamed.features.all_animals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velaphi.untamed.features.all_animals.models.AnimalModel
import com.velaphi.untamed.features.home.FilterModel
import com.velaphi.untamed.features.home.HomeModel
import com.velaphi.untamed.injection.UntamedAfricaComponent
import com.velaphi.untamed.repository.contracts.AllAnimalsRepository
import com.velaphi.untamed.repository.contracts.FilterRepository
import javax.inject.Inject

class AnimalsViewModel : ViewModel(), UntamedAfricaComponent.Injectable {

    @Inject
    lateinit var allAnimalsRepository: AllAnimalsRepository

    private val animalsItemListData: MutableLiveData<ArrayList<AnimalModel>> = MutableLiveData()
    private val exceptionStatus: MutableLiveData<Exception?> = MutableLiveData()

    override fun inject(untamedAfricaComponent: UntamedAfricaComponent?) {
        untamedAfricaComponent?.inject(this)
    }

    fun retrieveListOfAnimalsItems() {
        allAnimalsRepository.getListOfAnimalsFromFirebase(object : AllAnimalsRepository.RepositoryCallback {
            override fun onSuccess(response: ArrayList<AnimalModel>) {
                animalsItemListData.value = response
            }

            override fun onError(exception: Exception?) {
                exceptionStatus.value = exception
            }

        })
    }

    fun getHAnimalsItemMutableList(): MutableLiveData<ArrayList<AnimalModel>> {
        return animalsItemListData
    }

    fun getExceptionMessage(): MutableLiveData<Exception?> {
        return exceptionStatus
    }
}