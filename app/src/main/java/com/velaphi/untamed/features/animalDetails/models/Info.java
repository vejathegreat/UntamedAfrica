package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Info implements Parcelable {

    private String title;
    private String description;
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

    public Info() {
    }

    private int order;


    public Info(String title, String description, int order) {
        this.title = title;
        this.description = description;
        this.order = order;
    }

    protected Info(Parcel in) {
        title = in.readString();
        description = in.readString();
        order = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(order);
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

    public int getOrder() {
        return order;
    }


    @Override
    public int describeContents() {
        return 0;
    }

}
