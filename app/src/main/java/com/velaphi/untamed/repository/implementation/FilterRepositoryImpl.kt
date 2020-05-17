package com.velaphi.untamed.repository.implementation

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.velaphi.untamed.features.home.FilterModel
import com.velaphi.untamed.repository.contracts.FilterRepository
import javax.inject.Inject

class FilterRepositoryImpl(val firebaseFirestore: FirebaseFirestore) : FilterRepository {

    @Inject
    override fun getListOfFiltersFromFirebase(callback: FilterRepository.RepositoryCallback) {
        firebaseFirestore.collection("filters")
                .get().addOnCompleteListener { task: Task<QuerySnapshot?> ->
                    if (task.isSuccessful) {
                        val filerList = arrayListOf<FilterModel>()
                        for (document in task.result!!) {
                            val filterItem = document.toObject(FilterModel::class.java)
                            filerList.add(filterItem)
                        }
                        callback.onSuccess(filerList)
                    } else {
                        callback.onError(task.exception)
                    }
                }
    }

}