package com.velaphi.untamed.model;

public class Category extends Model {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_IMAGE = "image";

    public String name;
    public String image;

    public Category() {
    }

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
