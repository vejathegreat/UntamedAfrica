package com.velaphi.untamed.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velaphi.untamed.injection.UntamedAfricaComponent
import com.velaphi.untamed.repository.contracts.FilterRepository
import com.velaphi.untamed.repository.contracts.HomeRepository
import javax.inject.Inject

class HomeViewModel : ViewModel(), UntamedAfricaComponent.Injectable {

    @Inject
    lateinit var filterRepository: FilterRepository

    @Inject
    lateinit var homeRepository: HomeRepository

    private val filterModelItemListData: MutableLiveData<List<FilterModel>> = MutableLiveData()
    private val homeItemListData: MutableLiveData<ArrayList<HomeModel>> = MutableLiveData()
    private val exceptionStatus: MutableLiveData<Exception?> = MutableLiveData()
    private val exceptionHomeStatus: MutableLiveData<Exception?> = MutableLiveData()

    override fun inject(untamedAfricaComponent: UntamedAfricaComponent) {
        untamedAfricaComponent.inject(this)
    }

    fun retrieveListOfFilterItems() {
        filterRepository.getListOfFiltersFromFirebase(object : FilterRepository.RepositoryCallback {
            override fun onSuccess(response: ArrayList<FilterModel>) {
                filterModelItemListData.value = response
            }

            override fun onError(exception: Exception?) {
                exceptionStatus.value = exception
            }

        })
    }

    fun retrieveListOfHomeItems(homeItemName: String) {
        homeRepository.getListOfHomeItemsFromFirebase(homeItemName, object : HomeRepository.RepositoryCallback {

            override fun onSuccess(response: ArrayList<HomeModel>) {
                homeItemListData.value = response
            }

            override fun onError(exception: Exception?) {
                exceptionHomeStatus.value = exception
            }

        })
    }

    fun getFilterItemMutableList(): MutableLiveData<List<FilterModel>> {
        return filterModelItemListData
    }

    fun getHomeItemMutableList(): MutableLiveData<ArrayList<HomeModel>> {
        return homeItemListData
    }
    fun getExceptionMessage(): MutableLiveData<Exception?> {
        return exceptionStatus
    }
}