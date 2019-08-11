package com.velaphi.untamed.features.safaries;

import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SafariModel {

    private String image;
    private GeoPoint location;
    private String name;
    private String summary;

    public SafariModel() {
    }

    public SafariModel(String image, GeoPoint location, String name, String summary) {
        this.image = image;
        this.location = location;
        this.name = name;
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }
}
