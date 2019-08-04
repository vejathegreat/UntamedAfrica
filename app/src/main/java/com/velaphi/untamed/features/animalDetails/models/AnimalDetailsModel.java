package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class AnimalDetailsModel implements Parcelable {

    private Description description;
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
    private List<String> weight;
    private String quotable;
    private List<Fact> facts;
    private List<Video> videoList;

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
