package com.velaphi.untamed.features.animalList.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Fact {

    private String title;
    private String description;

    public Fact(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Fact() {
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
}
