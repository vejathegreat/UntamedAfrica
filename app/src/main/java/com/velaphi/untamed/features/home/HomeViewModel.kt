package com.velaphi.untamed.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velaphi.untamed.injection.UntamedAfricaComponent
import com.velaphi.untamed.repository.contracts.FilterRepository
import javax.inject.Inject

class HomeViewModel : ViewModel(), UntamedAfricaComponent.Injectable {

    @Inject
    lateinit var filterRepository: FilterRepository

    private val filterItemListData: MutableLiveData<List<Filter>> = MutableLiveData()
    private val exceptionStatus: MutableLiveData<Exception?> = MutableLiveData()

    override fun inject(untamedAfricaComponent: UntamedAfricaComponent) {
        untamedAfricaComponent.inject(this)
    }

    fun retrieveListOfFilterItems() {
        filterRepository.getListOfFiltersFromFirebase(object : FilterRepository.RepositoryCallback {
            override fun onSuccess(response: ArrayList<Filter>) {
                filterItemListData.value = response
            }

            override fun onError(exception: Exception?) {
                exceptionStatus?.value = exception
            }

        })
    }

    fun getFilterItemMutableList(): MutableLiveData<List<Filter>> {
        return filterItemListData
    }

    fun getExceptionMessage(): MutableLiveData<Exception?> {
        return exceptionStatus
    }
}