package com.velaphi.untamed.features.animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity
class Description : Parcelable {
    var heading: String? = null
    var details: String? = null

    constructor(heading: String?, details: String?) {
        this.heading = heading
        this.details = details
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        heading = `in`.readString()
        details = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(heading)
        dest.writeString(details)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Description?> = object : Parcelable.Creator<Description?> {
            override fun createFromParcel(`in`: Parcel): Description? {
                return Description(`in`)
            }

            override fun newArray(size: Int): Array<Description?> {
                return arrayOfNulls(size)
            }
        }
    }
}