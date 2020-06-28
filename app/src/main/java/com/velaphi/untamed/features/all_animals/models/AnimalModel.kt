package com.velaphi.untamed.features.all_animals.models

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
class AnimalModel(val name: String? = null,
                       val banner: Banner? = null,
                       val description: Description? = null) : Parcelable