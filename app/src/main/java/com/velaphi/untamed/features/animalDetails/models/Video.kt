package com.velaphi.untamed.features.animalDetails.models

import android.os.Parcel
import android.os.Parcelable

class Video : Parcelable {
    var caption: String? = null
        private set
    var source: String? = null
        private set
    var url: String? = null
        private set
    var type: String? = null
        private set

    constructor(caption: String?, source: String?, url: String?, type: String?) {
        this.caption = caption
        this.source = source
        this.url = url
        this.type = type
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        caption = `in`.readString()
        source = `in`.readString()
        url = `in`.readString()
        type = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(caption)
        dest.writeString(source)
        dest.writeString(url)
        dest.writeString(type)
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }

}