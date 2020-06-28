package com.velaphi.untamed.features.all_animals.models

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
class Banner(val image: String? = null,
                  val source: String? = null,
                  val tag: String? = null) : Parcelable