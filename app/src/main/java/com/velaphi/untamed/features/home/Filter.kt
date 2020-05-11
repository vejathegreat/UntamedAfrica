package com.velaphi.untamed.features.home

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class Filter(val name: String? = null,
                  val key: String? = null) : Parcelable