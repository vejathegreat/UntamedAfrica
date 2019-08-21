package com.velaphi.untamed.features.about;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AboutModel {

    private String title;
    private String description;

    public AboutModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public AboutModel() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
