package com.velaphi.untamed.repository.implementation

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.velaphi.untamed.features.home.HomeModel
import com.velaphi.untamed.repository.contracts.HomeRepository

class HomeRepositoryImpl(val firebaseFirestore: FirebaseFirestore) : HomeRepository {

    override fun getListOfHomeItemsFromFirebase(homeItemName: String, callback: HomeRepository.RepositoryCallback) {
        firebaseFirestore.collection(homeItemName)
                .orderBy(LEVEL, Query.Direction.ASCENDING)
                .get().addOnCompleteListener { task: Task<QuerySnapshot> ->
                    if (task.isSuccessful) {
                        val list: ArrayList<HomeModel> = ArrayList()
                        for (document in task.result!!) {
                            val homeModel = document.toObject(HomeModel::class.java)
                            list.add(homeModel)
                        }
                        Log.d(TAG, list.toString())
                        callback.onSuccess(list)
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.exception)
                        callback.onError(task.exception)
                    }
                }
    }

    companion object {
        private const val TAG = "HomeRepository"
        private const val LEVEL = "level"
    }
}