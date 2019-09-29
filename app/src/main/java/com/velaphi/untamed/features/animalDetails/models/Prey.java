package com.velaphi.untamed.features.animalDetails.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Prey implements Parcelable {
    private String name;
    private String image;

    public Prey() {
    }

    protected Prey(Parcel in) {
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<Prey> CREATOR = new Creator<Prey>() {
        @Override
        public Prey createFromParcel(Parcel in) {
            return new Prey(in);
        }

        @Override
        public Prey[] newArray(int size) {
            return new Prey[size];
        }
    };

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
