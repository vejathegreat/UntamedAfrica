package com.velaphi.untamed.features.animalList.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class AnimalDetailsModel implements Parcelable {
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
    private Description description;
    private String diet;
    private List<Fact> facts;
    private String gestation;
    private Habitat habitat;
    private String image;
    private List<String> imageList;
    private String key;
    private int level;
    private String lifeSpan;
    private String name;
    private List<Challenge> challenges;
    private List<String> predators;
    private List<String> scientificNames;
    private String size;
    private List<String> videoList;
    private List<String> weight;


    public AnimalDetailsModel() {
    }

    private String quotable;


    public AnimalDetailsModel(List<Challenge> challenges, Description description, String diet, List<Fact> facts, String gestation, Habitat habitat, String image, List<String> imageList, String key, int level, String lifeSpan, String name, String quotable, List<String> predators, List<String> scientificNames, String size, List<String> videoList, List<String> weight) {
        this.challenges = challenges;
        this.description = description;
        this.diet = diet;
        this.facts = facts;
        this.gestation = gestation;
        this.habitat = habitat;
        this.image = image;
        this.imageList = imageList;
        this.key = key;
        this.level = level;
        this.lifeSpan = lifeSpan;
        this.name = name;
        this.quotable = quotable;
        this.predators = predators;
        this.scientificNames = scientificNames;
        this.size = size;
        this.videoList = videoList;
        this.weight = weight;
    }

    protected AnimalDetailsModel(Parcel in) {
        challenges = in.createTypedArrayList(Challenge.CREATOR);
        description = in.readParcelable(Description.class.getClassLoader());
        diet = in.readString();
        facts = in.createTypedArrayList(Fact.CREATOR);
        gestation = in.readString();
        habitat = in.readParcelable(Habitat.class.getClassLoader());
        image = in.readString();
        imageList = in.createStringArrayList();
        key = in.readString();
        level = in.readInt();
        lifeSpan = in.readString();
        name = in.readString();
        quotable = in.readString();
        predators = in.createStringArrayList();
        scientificNames = in.createStringArrayList();
        size = in.readString();
        videoList = in.createStringArrayList();
        weight = in.createStringArrayList();
    }

    public String getName() {
        return name;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public Description getDescription() {
        return description;
    }

    public String getDiet() {
        return diet;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public String getGestation() {
        return gestation;
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

    public String getLifeSpan() {
        return lifeSpan;
    }

    public List<String> getPredators() {
        return predators;
    }

    public List<String> getScientificNames() {
        return scientificNames;
    }

    public String getSize() {
        return size;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(challenges);
        dest.writeParcelable(description, flags);
        dest.writeString(diet);
        dest.writeTypedList(facts);
        dest.writeString(gestation);
        dest.writeParcelable(habitat, flags);
        dest.writeString(image);
        dest.writeStringList(imageList);
        dest.writeString(key);
        dest.writeInt(level);
        dest.writeString(lifeSpan);
        dest.writeString(name);
        dest.writeString(quotable);
        dest.writeStringList(predators);
        dest.writeStringList(scientificNames);
        dest.writeString(size);
        dest.writeStringList(videoList);
        dest.writeStringList(weight);
    }
}
