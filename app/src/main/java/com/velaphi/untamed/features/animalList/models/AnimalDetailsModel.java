package com.velaphi.untamed.features.animalList.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class AnimalDetailsModel {
    private List<Challange> challanges;
    private Description description;
    private String diet;
    private List<Fact> facts;
    private String gestation;
    private Habitat habitat;
    private String image;
    private List<String> imageList;
    private String key;
    private int level;
    private String lifeSpan;
    private String name;
    private List<String> predators;
    private List<String> scientificNames;
    private String size;
    private List<String> videoList;
    private List<String> weight;


    public AnimalDetailsModel() {
    }

    public AnimalDetailsModel(List<Challange> challanges, Description description, String diet, List<Fact> facts, String gestation, Habitat habitat, String image, List<String> imageList, String key, int level, String lifeSpan, String name, List<String> predators, List<String> scientificNames, String size, List<String> videoList, List<String> weight) {
        this.challanges = challanges;
        this.description = description;
        this.diet = diet;
        this.facts = facts;
        this.gestation = gestation;
        this.habitat = habitat;
        this.image = image;
        this.imageList = imageList;
        this.key = key;
        this.level = level;
        this.lifeSpan = lifeSpan;
        this.name = name;
        this.predators = predators;
        this.scientificNames = scientificNames;
        this.size = size;
        this.videoList = videoList;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public List<Challange> getChallanges() {
        return challanges;
    }

    public Description getDescription() {
        return description;
    }

    public String getDiet() {
        return diet;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public String getGestation() {
        return gestation;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public String getImage() {
        return image;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public String getKey() {
        return key;
    }

    public int getLevel() {
        return level;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public List<String> getPredators() {
        return predators;
    }

    public List<String> getScientificNames() {
        return scientificNames;
    }

    public String getSize() {
        return size;
    }

    public List<String> getVideoList() {
        return videoList;
    }

    public List<String> getWeight() {
        return weight;
    }
}
