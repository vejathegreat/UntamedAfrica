package com.velaphi.untamed.features.animalDetails.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<String> restoreList(String listOfString) {
        return new Gson().fromJson(listOfString, new TypeToken<List<String>>() {
        }.getType());
    }

    @TypeConverter
    public static String saveList(List<String> listOfString) {
        return new Gson().toJson(listOfString);
    }


    @TypeConverter
    public static String fromBasicInfoList(List<Info> infoList) {
        return new Gson().toJson(infoList);
    }

    @TypeConverter
    public static List<Info> toBasicInfoList(String basicInfoJson) {
        return new Gson().fromJson(basicInfoJson, new TypeToken<List<Info>>() {
        }.getType());
    }


    @TypeConverter
    public static String fromFactList(List<Fact> factList) {
        return new Gson().toJson(factList);
    }

    @TypeConverter
    public static List<Fact> toFactList(String factJson) {
        return new Gson().fromJson(factJson, new TypeToken<List<Fact>>() {
        }.getType());
    }

    @TypeConverter
    public static String fromChallengeList(List<Challenge> challengeList) {
        return new Gson().toJson(challengeList);
    }

    @TypeConverter
    public static List<Challenge> toChallengetList(String challengesJson) {
        return new Gson().fromJson(challengesJson, new TypeToken<List<Challenge>>() {
        }.getType());
    }


    @TypeConverter
    public static String fromVideoList(List<Video> videoList) {
        return new Gson().toJson(videoList);
    }

    @TypeConverter
    public static ArrayList<Video> toVideoList(String videosJson) {
        return new Gson().fromJson(videosJson, new TypeToken<List<Video>>() {
        }.getType());
    }


    @TypeConverter
    public static Habitat restoreHabit(String habitatString) {
        return new Gson().fromJson(habitatString, Habitat.class);
    }

    @TypeConverter
    public static String saveHabitatToJson(Habitat habitat) {
        return new Gson().toJson(habitat);
    }

    @TypeConverter
    public static Description restoreDescription(String descriptionString) {
        return new Gson().fromJson(descriptionString, Description.class);
    }

    @TypeConverter
    public static String saveDescriptionToJson(Description description) {
        return new Gson().toJson(description);
    }
}
