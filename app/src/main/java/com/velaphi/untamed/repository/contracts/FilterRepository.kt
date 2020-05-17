package com.velaphi.untamed.repository.contracts

import com.velaphi.untamed.features.home.FilterModel
import java.lang.Exception

interface FilterRepository {

    interface RepositoryCallback {
        fun onSuccess(response: ArrayList<FilterModel>)
        fun onError(exception: Exception?)
    }

    fun getListOfFiltersFromFirebase(callback: RepositoryCallback)
}