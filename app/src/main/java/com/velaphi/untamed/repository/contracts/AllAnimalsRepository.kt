package com.velaphi.untamed.repository.contracts

import com.velaphi.untamed.features.all_animals.models.AnimalModel

interface AllAnimalsRepository {

    interface RepositoryCallback {
        fun onSuccess(response: ArrayList<AnimalModel>)
        fun onError(exception: Exception?)
    }

    fun getListOfAnimalsFromFirebase(callback: RepositoryCallback)

}