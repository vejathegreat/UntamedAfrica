package com.velaphi.untamed.features.old_animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Prey : Parcelable {
    var name: String? = null
    var image: String? = null

    constructor() {}
    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        image = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(image)
    }

    companion object CREATOR : Parcelable.Creator<Prey> {
        override fun createFromParcel(parcel: Parcel): Prey {
            return Prey(parcel)
        }

        override fun newArray(size: Int): Array<Prey?> {
            return arrayOfNulls(size)
        }
    }

}