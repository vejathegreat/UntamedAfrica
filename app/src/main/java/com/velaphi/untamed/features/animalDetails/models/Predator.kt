package com.velaphi.untamed.features.animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Predator : Parcelable {
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

    companion object CREATOR : Parcelable.Creator<Predator> {
        override fun createFromParcel(parcel: Parcel): Predator {
            return Predator(parcel)
        }

        override fun newArray(size: Int): Array<Predator?> {
            return arrayOfNulls(size)
        }
    }

}