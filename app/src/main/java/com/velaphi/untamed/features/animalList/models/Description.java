package com.velaphi.untamed.features.animalList.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Description {
    private String heading;
    private String details;

    public Description(String heading, String details) {
        this.heading = heading;
        this.details = details;
    }

    public Description() {
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
}
