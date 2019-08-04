package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class AnimalDetailsModel implements Parcelable {

    private Description description;
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
    private Habitat habitat;
    private String image;
    private List<String> imageList;
    private List<Info> basicInfo;
    private List<String> located;
    private String key;
    private int level;
    private String name;
    private List<Challenge> challenges;
    private List<String> predators;
    private List<String> scientificNames;
    private List<String> videoList;
    private List<String> weight;
    private String quotable;
    private List<Fact> facts;

    public AnimalDetailsModel() {
    }

    public Description getDescription() {
        return description;
    }


    public AnimalDetailsModel(Description description, List<Info> basicInfo, Habitat habitat, String image, List<String> imageList, List<String> located, List<Fact> facts, String key, int level, String name, List<Challenge> challenges, List<String> predators, List<String> scientificNames, List<String> videoList, List<String> weight, String quotable) {
        this.description = description;
        this.basicInfo = basicInfo;
        this.habitat = habitat;
        this.image = image;
        this.imageList = imageList;
        this.located = located;
        this.facts = facts;
        this.key = key;
        this.level = level;
        this.name = name;
        this.challenges = challenges;
        this.predators = predators;
        this.scientificNames = scientificNames;
        this.videoList = videoList;
        this.weight = weight;
        this.quotable = quotable;
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

    protected AnimalDetailsModel(Parcel in) {
        description = in.readParcelable(Description.class.getClassLoader());
        basicInfo = in.createTypedArrayList(Info.CREATOR);
        habitat = in.readParcelable(Habitat.class.getClassLoader());
        image = in.readString();
        imageList = in.createStringArrayList();
        located = in.createStringArrayList();
        facts = in.createTypedArrayList(Fact.CREATOR);
        key = in.readString();
        level = in.readInt();
        name = in.readString();
        challenges = in.createTypedArrayList(Challenge.CREATOR);
        predators = in.createStringArrayList();
        scientificNames = in.createStringArrayList();
        videoList = in.createStringArrayList();
        weight = in.createStringArrayList();
        quotable = in.readString();
    }

    public static Creator<AnimalDetailsModel> getCREATOR() {
        return CREATOR;
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

    public List<String> getVideoList() {
        return videoList;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(description, flags);
        dest.writeTypedList(basicInfo);
        dest.writeParcelable(habitat, flags);
        dest.writeString(image);
        dest.writeStringList(imageList);
        dest.writeStringList(located);
        dest.writeTypedList(facts);
        dest.writeString(key);
        dest.writeInt(level);
        dest.writeString(name);
        dest.writeTypedList(challenges);
        dest.writeStringList(predators);
        dest.writeStringList(scientificNames);
        dest.writeStringList(videoList);
        dest.writeStringList(weight);
        dest.writeString(quotable);
    }
}
