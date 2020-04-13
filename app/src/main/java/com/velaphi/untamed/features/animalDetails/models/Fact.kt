package com.velaphi.untamed.features.animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Fact : Parcelable {
    var title: String? = null
    var description: String? = null

    constructor(title: String?, description: String?) {
        this.title = title
        this.description = description
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        title = `in`.readString()
        description = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
    }

    companion object CREATOR : Parcelable.Creator<Fact> {
        override fun createFromParcel(parcel: Parcel): Fact {
            return Fact(parcel)
        }

        override fun newArray(size: Int): Array<Fact?> {
            return arrayOfNulls(size)
        }
    }
}