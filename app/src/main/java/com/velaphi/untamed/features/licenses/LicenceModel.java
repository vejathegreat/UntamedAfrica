package com.velaphi.untamed.features.licenses;


import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class LicenceModel {

    private String name;
    private String url;

    public LicenceModel() {
    }

    public LicenceModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
