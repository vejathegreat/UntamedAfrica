package com.velaphi.untamed.repository.contracts

import com.velaphi.untamed.features.home.HomeModel

interface HomeRepository{

    interface RepositoryCallback {
        fun onSuccess(response: ArrayList<HomeModel>)
        fun onError(exception: Exception?)
    }

    fun getListOfHomeItemsFromFirebase(homeItemName: String, callback: RepositoryCallback)

}