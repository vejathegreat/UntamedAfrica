package com.velaphi.untamed.features.old_animalDetails.models

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
@Entity(tableName = "favorite_animals")
class AnimalDetailsModel : Parcelable {
    @TypeConverters(Converters::class)
    var description: Description? = null
    @TypeConverters(Converters::class)
    var habitat: Habitat? = null
    @ColumnInfo
    @SerializedName("image")
    @Expose
    var image: String? = null
    @ColumnInfo(name = "image_list")
    @TypeConverters(Converters::class)
    var imageList: List<String>? = null
    @ColumnInfo(name = "basic_Info")
    @TypeConverters(Converters::class)
    var basicInfo: List<Info>? = null
    @ColumnInfo(name = "located")
    @TypeConverters(Converters::class)
    var located: List<Country>? = null
    @ColumnInfo
    @SerializedName("key")
    @Expose
    var key: String? = null
    @ColumnInfo
    @SerializedName("level")
    @Expose
    var level = 0
    @PrimaryKey
    @SerializedName("name")
    @Expose
    @NonNull
    var name: String? = null
    @ColumnInfo(name = "challenges")
    @TypeConverters(Converters::class)
    var challenges: List<Challenge>? = null
    @ColumnInfo(name = "predators")
    @TypeConverters(Converters::class)
    var predators: List<Predator>? = null
    @ColumnInfo(name = "prey")
    @TypeConverters(Converters::class)
    var prey: List<Prey>? = null
    @ColumnInfo(name = "scientific_names")
    @TypeConverters(Converters::class)
    var scientificNames: List<String>? = null
    @ColumnInfo(name = "weight")
    @TypeConverters(Converters::class)
    var weight: List<String>? = null
    @SerializedName("quotable")
    @Expose
    var quotable: String? = null
    @ColumnInfo(name = "facts")
    @TypeConverters(Converters::class)
    var facts: List<Fact>? = null
    @ColumnInfo(name = "video_list")
    @TypeConverters(Converters::class)
    var videoList: ArrayList<Video>? = null

    constructor(parcel: Parcel) : this() {
        description = parcel.readParcelable(Description::class.java.classLoader)
        habitat = parcel.readParcelable(Habitat::class.java.classLoader)
        image = parcel.readString()
        imageList = parcel.createStringArrayList()
        basicInfo = parcel.createTypedArrayList(Info)
        located = parcel.createTypedArrayList(Country)
        key = parcel.readString()
        level = parcel.readInt()
        name = parcel.readString()
        challenges = parcel.createTypedArrayList(Challenge)
        predators = parcel.createTypedArrayList(Predator)
        prey = parcel.createTypedArrayList(Prey)
        scientificNames = parcel.createStringArrayList()
        weight = parcel.createStringArrayList()
        quotable = parcel.readString()
        facts = parcel.createTypedArrayList(Fact)
    }

    constructor() {}


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(description, flags)
        parcel.writeParcelable(habitat, flags)
        parcel.writeString(image)
        parcel.writeStringList(imageList)
        parcel.writeTypedList(basicInfo)
        parcel.writeTypedList(located)
        parcel.writeString(key)
        parcel.writeInt(level)
        parcel.writeString(name)
        parcel.writeTypedList(challenges)
        parcel.writeTypedList(predators)
        parcel.writeTypedList(prey)
        parcel.writeStringList(scientificNames)
        parcel.writeStringList(weight)
        parcel.writeString(quotable)
        parcel.writeTypedList(facts)
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