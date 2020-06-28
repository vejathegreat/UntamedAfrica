package com.velaphi.untamed.features.all_animals

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@IgnoreExtraProperties
class AnimalDetailsModel : Parcelable {
    @ColumnInfo
    @Expose
    var image: String? = null

    @PrimaryKey
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        image = parcel.readString()
        name = parcel.readString()
    }

    constructor() {}


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<AnimalDetailsModel> {
        override fun createFromParcel(parcel: Parcel): AnimalDetailsModel {
            return AnimalDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<AnimalDetailsModel?> {
            return arrayOfNulls(size)
        }
    }

}