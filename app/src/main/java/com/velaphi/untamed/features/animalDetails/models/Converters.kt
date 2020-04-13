package com.velaphi.untamed.features.animalDetails.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object Converters {
    @JvmStatic
    @TypeConverter
    fun restoreList(listOfString: String?): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @JvmStatic
    @TypeConverter
    fun saveList(listOfString: List<String?>?): String {
        return Gson().toJson(listOfString)
    }

    @JvmStatic
    @TypeConverter
    fun fromBasicInfoList(infoList: List<Info?>?): String {
        return Gson().toJson(infoList)
    }

    @JvmStatic
    @TypeConverter
    fun toBasicInfoList(basicInfoJson: String?): List<Info> {
        return Gson().fromJson(basicInfoJson, object : TypeToken<List<Info?>?>() {}.type)
    }

    @JvmStatic
    @TypeConverter
    fun fromBasicCountryList(countryList: List<Country?>?): String {
        return Gson().toJson(countryList)
    }

    @JvmStatic
    @TypeConverter
    fun toCountryList(countryListJson: String?): List<Country> {
        return Gson().fromJson(countryListJson, object : TypeToken<List<Country?>?>() {}.type)
    }
    @JvmStatic
    @TypeConverter
    fun fromPredatorList(predatorList: List<Predator?>?): String {
        return Gson().toJson(predatorList)
    }

    @JvmStatic
    @TypeConverter
    fun toPredatorList(predatorListJson: String?): List<Predator> {
        return Gson().fromJson(predatorListJson, object : TypeToken<List<Predator?>?>() {}.type)
    }

    @JvmStatic
    @TypeConverter
    fun toPreyList(preyListJson: String?): List<Prey> {
        return Gson().fromJson(preyListJson, object : TypeToken<List<Prey?>?>() {}.type)
    }

    @JvmStatic
    @TypeConverter
    fun fromPreyList(preyList: List<Prey?>?): String {
        return Gson().toJson(preyList)
    }

    @JvmStatic
    @TypeConverter
    fun fromFactList(factList: List<Fact?>?): String {
        return Gson().toJson(factList)
    }

    @JvmStatic
    @TypeConverter
    fun toFactList(factJson: String?): List<Fact> {
        return Gson().fromJson(factJson, object : TypeToken<List<Fact?>?>() {}.type)
    }

    @JvmStatic
    @TypeConverter
    fun fromChallengeList(challengeList: List<Challenge?>?): String {
        return Gson().toJson(challengeList)
    }

    @JvmStatic
    @TypeConverter
    fun toChallengeList(challengesJson: String?): List<Challenge> {
        return Gson().fromJson(challengesJson, object : TypeToken<List<Challenge?>?>() {}.type)
    }

    @JvmStatic
    @TypeConverter
    fun fromVideoList(videoList: List<Video?>?): String {
        return Gson().toJson(videoList)
    }

    @JvmStatic
    @TypeConverter
    fun toVideoList(videosJson: String?): ArrayList<Video> {
        return Gson().fromJson(videosJson, object : TypeToken<List<Video?>?>() {}.type)
    }

    @JvmStatic
    @TypeConverter
    fun restoreHabit(habitatString: String?): Habitat {
        return Gson().fromJson(habitatString, Habitat::class.java)
    }

    @JvmStatic
    @TypeConverter
    fun saveHabitatToJson(habitat: Habitat?): String {
        return Gson().toJson(habitat)
    }

    @JvmStatic
    @TypeConverter
    fun restoreDescription(descriptionString: String?): Description {
        return Gson().fromJson(descriptionString, Description::class.java)
    }

    @JvmStatic
    @TypeConverter
    fun saveDescriptionToJson(description: Description?): String {
        return Gson().toJson(description)
    }
}