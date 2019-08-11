package com.velaphi.untamed.features.categories;

import com.google.firebase.firestore.IgnoreExtraProperties;


@IgnoreExtraProperties
public class CategoryModel {

    private String name;
    private String image;
    private String description;
    private int level;

    public String getName() {
        return name;
    }


    public String getImage() {
        return image;
    }


    public String getDescription() {
        return description;
    }


    public int getLevel() {
        return level;
    }

}
