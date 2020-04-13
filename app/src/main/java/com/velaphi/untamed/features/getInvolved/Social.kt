package com.velaphi.untamed.features.getInvolved

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize

data class Social (val name: String? = null,
                  val url: String? = null): Parcelable