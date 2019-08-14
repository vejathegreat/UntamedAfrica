package com.velaphi.untamed.features.safaries;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class SafariModel implements Parcelable {

    public static final Creator<SafariModel> CREATOR = new Creator<SafariModel>() {
        @Override
        public SafariModel createFromParcel(Parcel in) {
            return new SafariModel(in);
        }

        @Override
        public SafariModel[] newArray(int size) {
            return new SafariModel[size];
        }
    };
    private String details;
    private String image;
    private String name;
    private String summary;
    private String address;
    private String web;
    private List<String> imageList;
    private double latitude, longitude;

    public SafariModel() {
    }

    private GeoPoint coordinates;

    protected SafariModel(Parcel in) {
        details = in.readString();
        address = in.readString();
        image = in.readString();
        name = in.readString();
        summary = in.readString();
        web = in.readString();
        imageList = in.createStringArrayList();
        latitude = in.readDouble();
        longitude = in.readDouble();
        coordinates = new GeoPoint(latitude, longitude);
    }

    public String getDetails() {
        return details;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getWeb() {
        return web;
    }

    public GeoPoint getCoordinates() {
        return coordinates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(details);
        dest.writeString(address);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(summary);
        dest.writeString(web);
        dest.writeStringList(imageList);
        dest.writeDouble(coordinates.getLatitude());
        dest.writeDouble(coordinates.getLongitude());
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
