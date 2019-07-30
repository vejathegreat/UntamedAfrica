package com.velaphi.untamed.features.animalList.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Habitat implements Parcelable {
    private String summary;
    private String description;

    public Habitat() {
    }

    public Habitat(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }

    public static final Creator<Habitat> CREATOR = new Creator<Habitat>() {
        @Override
        public Habitat createFromParcel(Parcel in) {
            return new Habitat(in);
        }

        @Override
        public Habitat[] newArray(int size) {
            return new Habitat[size];
        }
    };

    protected Habitat(Parcel in) {
        summary = in.readString();
        description = in.readString();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(summary);
        dest.writeString(description);
    }
}
