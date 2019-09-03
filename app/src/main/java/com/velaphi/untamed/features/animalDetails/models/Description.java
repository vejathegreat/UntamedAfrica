package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
@Entity
public class Description implements Parcelable {
    private String heading;
    private String details;

    public Description(String heading, String details) {
        this.heading = heading;
        this.details = details;
    }

    public Description() {
    }

    public static final Creator<Description> CREATOR = new Creator<Description>() {
        @Override
        public Description createFromParcel(Parcel in) {
            return new Description(in);
        }

        @Override
        public Description[] newArray(int size) {
            return new Description[size];
        }
    };

    protected Description(Parcel in) {
        heading = in.readString();
        details = in.readString();
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(heading);
        dest.writeString(details);
    }
}
