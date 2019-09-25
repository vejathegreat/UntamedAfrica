package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Predator implements Parcelable {
    private String name;
    private String image;

    public Predator() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    protected Predator(Parcel in) {
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<Predator> CREATOR = new Creator<Predator>() {
        @Override
        public Predator createFromParcel(Parcel in) {
            return new Predator(in);
        }

        @Override
        public Predator[] newArray(int size) {
            return new Predator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
    }
}
