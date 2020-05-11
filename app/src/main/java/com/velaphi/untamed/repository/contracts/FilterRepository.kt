package com.velaphi.untamed.repository.contracts

import com.velaphi.untamed.features.home.Filter
import java.lang.Exception

interface FilterRepository {

    interface RepositoryCallback {
        fun onSuccess(response: ArrayList<Filter>)
        fun onError(exception: Exception?)
    }

    fun getListOfFiltersFromFirebase(callback: RepositoryCallback)
}