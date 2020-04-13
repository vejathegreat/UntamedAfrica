package com.velaphi.untamed.features.animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Info : Parcelable {
    var title: String? = null
        private set
    var description: String? = null
        private set

    constructor() {}

    var order = 0
        private set

    constructor(title: String?, description: String?, order: Int) {
        this.title = title
        this.description = description
        this.order = order
    }

    protected constructor(`in`: Parcel) {
        title = `in`.readString()
        description = `in`.readString()
        order = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeInt(order)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Info> {
        override fun createFromParcel(parcel: Parcel): Info {
            return Info(parcel)
        }

        override fun newArray(size: Int): Array<Info?> {
            return arrayOfNulls(size)
        }
    }
}