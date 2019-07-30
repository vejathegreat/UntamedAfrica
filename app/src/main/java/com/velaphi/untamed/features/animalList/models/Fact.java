package com.velaphi.untamed.features.animalList.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Fact implements Parcelable {

    private String title;
    private String description;

    public Fact(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Fact() {
    }

    public static final Creator<Fact> CREATOR = new Creator<Fact>() {
        @Override
        public Fact createFromParcel(Parcel in) {
            return new Fact(in);
        }

        @Override
        public Fact[] newArray(int size) {
            return new Fact[size];
        }
    };

    protected Fact(Parcel in) {
        title = in.readString();
        description = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        dest.writeString(title);
        dest.writeString(description);
    }
}
