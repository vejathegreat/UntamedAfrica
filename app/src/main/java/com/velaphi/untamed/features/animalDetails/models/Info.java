package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
    private String title;
    private String description;

    public Info() {
    }

    public Info(String title, String description) {
        this.title = title;
        this.description = description;
    }

    protected Info(Parcel in) {
        title = in.readString();
        description = in.readString();
    }

    public static Creator<Info> getCREATOR() {
        return CREATOR;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
