package com.velaphi.untamed.features.getInvolved

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class FoundationModel(val content: String? = null,
                           val helpUrl: String? = null,
                           val image: String? = null,
                           val mainSite: String? = null,
                           val name: String? = null,
                           val tagLine: String? = null,
                           val mission: String? = null,
                           val socials: List<Social>? = null): Parcelable
