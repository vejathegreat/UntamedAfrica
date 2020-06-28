package com.velaphi.untamed.repository.implementation

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.velaphi.untamed.features.all_animals.models.AnimalModel
import com.velaphi.untamed.repository.contracts.AllAnimalsRepository

class AllAnimalsRepositoryImpl(val firebaseFirestore: FirebaseFirestore): AllAnimalsRepository {

    override fun getListOfAnimalsFromFirebase(callback: AllAnimalsRepository.RepositoryCallback) {
        firebaseFirestore.collection(searchId)
                .orderBy(NAME, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener { task: Task<QuerySnapshot> ->
            if(task.isSuccessful) {
                val list: ArrayList<AnimalModel> = ArrayList()
                for (document in task.result!!){
                    val animalModel = document.toObject(AnimalModel::class.java)
                    list.add(animalModel)
                }
                Log.d(TAG, list.toString())
                callback.onSuccess(list)
            }else{
                Log.d(TAG, "Error getting documents: ", task.exception)
            callback.onError(task.exception)
            }
        }
    }

    companion object {
        private const val searchId = "species"
        private const val TAG = "AllAnimalsRepository"
        private const val NAME = "name"
    }
}