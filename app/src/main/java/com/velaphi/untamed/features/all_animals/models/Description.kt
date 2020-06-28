package com.velaphi.untamed.features.all_animals.models

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
class Description(val content: String? = null,
                       val source: String? = null,
                       val link: String? = null,
                       val title: String? = null) : Parcelable