package com.velaphi.untamed.features.old_animalDetails.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity
class Habitat : Parcelable {
    var summary: String? = null
    var description: String? = null

    constructor() {}
    @Ignore
    constructor(summary: String?, description: String?) {
        this.summary = summary
        this.description = description
    }

    protected constructor(`in`: Parcel) {
        summary = `in`.readString()
        description = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(summary)
        dest.writeString(description)
    }

    companion object CREATOR : Parcelable.Creator<Habitat> {
        override fun createFromParcel(parcel: Parcel): Habitat {
            return Habitat(parcel)
        }

        override fun newArray(size: Int): Array<Habitat?> {
            return arrayOfNulls(size)
        }
    }

}