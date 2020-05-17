package com.velaphi.untamed.features.home

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class HomeModel(val name: String? = null,
                     val image: String? = null,
                     val description : String? = null,
                     val level: Int? = 0) : Parcelable {
}