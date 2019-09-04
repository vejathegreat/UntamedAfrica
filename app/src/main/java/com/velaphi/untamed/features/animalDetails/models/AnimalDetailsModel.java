package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@IgnoreExtraProperties
@Entity(tableName = "favorite_animals")
public class AnimalDetailsModel implements Parcelable {

    @TypeConverters(Converters.class)
    private Description description;

    @TypeConverters(Converters.class)
    private Habitat habitat;

    @ColumnInfo
    @SerializedName("image")
    @Expose
    private String image;

    @ColumnInfo(name = "image_list")
    @TypeConverters(Converters.class)
    private List<String> imageList = null;

    @ColumnInfo(name = "basic_Info")
    @TypeConverters(Converters.class)
    private List<Info> basicInfo = null;

    @ColumnInfo(name = "located")
    @TypeConverters(Converters.class)
    private List<String> located;

    @ColumnInfo
    @SerializedName("key")
    @Expose
    private String key;

    @ColumnInfo
    @SerializedName("level")
    @Expose
    private int level;

    @PrimaryKey
    @SerializedName("name")
    @Expose
    @NonNull
    private String name;

    @ColumnInfo(name = "challenges")
    @TypeConverters(Converters.class)
    private List<Challenge> challenges = null;

    @ColumnInfo(name = "predators")
    @TypeConverters(Converters.class)
    private List<String> predators = null;

    @ColumnInfo(name = "scientific_names")
    @TypeConverters(Converters.class)
    private List<String> scientificNames = null;

    @ColumnInfo(name = "weight")
    @TypeConverters(Converters.class)
    private List<String> weight = null;

    @SerializedName("quotable")
    @Expose
    private String quotable;

    @ColumnInfo(name = "facts")
    @TypeConverters(Converters.class)
    private List<Fact> facts = null;

    @ColumnInfo(name = "video_list")
    @TypeConverters(Converters.class)
    private List<Video> videoList = null;

    public static final Creator<AnimalDetailsModel> CREATOR = new Creator<AnimalDetailsModel>() {
        @Override
        public AnimalDetailsModel createFromParcel(Parcel in) {
            return new AnimalDetailsModel(in);
        }

        @Override
        public AnimalDetailsModel[] newArray(int size) {
            return new AnimalDetailsModel[size];
        }
    };

    protected AnimalDetailsModel(Parcel in) {
        description = in.readParcelable(Description.class.getClassLoader());
        habitat = in.readParcelable(Habitat.class.getClassLoader());
        image = in.readString();
        imageList = in.createStringArrayList();
        basicInfo = in.createTypedArrayList(Info.CREATOR);
        located = in.createStringArrayList();
        key = in.readString();
        level = in.readInt();
        name = in.readString();
        challenges = in.createTypedArrayList(Challenge.CREATOR);
        predators = in.createStringArrayList();
        scientificNames = in.createStringArrayList();
        videoList = in.createTypedArrayList(Video.CREATOR);
        weight = in.createStringArrayList();
        quotable = in.readString();
        facts = in.createTypedArrayList(Fact.CREATOR);
    }


    public AnimalDetailsModel() {
    }

    public static Creator<AnimalDetailsModel> getCREATOR() {
        return CREATOR;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public Description getDescription() {
        return description;
    }


    public Habitat getHabitat() {
        return habitat;
    }

    public String getImage() {
        return image;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public String getKey() {
        return key;
    }

    public int getLevel() {
        return level;
    }

    public List<Info> getBasicInfo() {
        return basicInfo;
    }

    public List<String> getLocated() {
        return located;
    }

    public List<String> getPredators() {
        return predators;
    }

    public List<String> getScientificNames() {
        return scientificNames;
    }

    public List<String> getWeight() {
        return weight;
    }

    public String getQuotable() {
        return quotable;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public String getName() {
        return name;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public void setBasicInfo(List<Info> basicInfo) {
        this.basicInfo = basicInfo;
    }

    public void setLocated(List<String> located) {
        this.located = located;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public void setPredators(List<String> predators) {
        this.predators = predators;
    }

    public void setScientificNames(List<String> scientificNames) {
        this.scientificNames = scientificNames;
    }

    public void setWeight(List<String> weight) {
        this.weight = weight;
    }

    public void setQuotable(String quotable) {
        this.quotable = quotable;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(description, flags);
        dest.writeParcelable(habitat, flags);
        dest.writeString(image);
        dest.writeStringList(imageList);
        dest.writeTypedList(basicInfo);
        dest.writeStringList(located);
        dest.writeString(key);
        dest.writeInt(level);
        dest.writeString(name);
        dest.writeTypedList(challenges);
        dest.writeStringList(predators);
        dest.writeStringList(scientificNames);
        dest.writeTypedList(videoList);
        dest.writeStringList(weight);
        dest.writeString(quotable);
        dest.writeTypedList(facts);
    }
}
