package com.velaphi.untamed.features.old_animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Challenge : Parcelable {
    var title: String? = null
    var details: String? = null

    constructor() {}

    constructor(title: String?, details: String?) {
        this.title = title
        this.details = details
    }

    protected constructor(`in`: Parcel) {
        title = `in`.readString()
        details = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(details)
    }

    companion object CREATOR : Parcelable.Creator<Challenge> {
        override fun createFromParcel(parcel: Parcel): Challenge {
            return Challenge(parcel)
        }

        override fun newArray(size: Int): Array<Challenge?> {
            return arrayOfNulls(size)
        }
    }

}