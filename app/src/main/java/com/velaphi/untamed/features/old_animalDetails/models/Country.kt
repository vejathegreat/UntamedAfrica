package com.velaphi.untamed.features.old_animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Country : Parcelable {

    var name: String? = null
        private set
    var flag: String? = null
        private set

    constructor() {}

    constructor(name: String?, flag: String?) {
        this.name = name
        this.flag = flag
    }

    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        flag = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(flag)
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}