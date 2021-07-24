package com.example.appprojectsfetcher.model;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("score")
    private String score;

    @SerializedName("owner")
    private Owner owner;

    public Repository(String name, String description, String score, Owner owner) {
        this.name = name;
        this.description = description;
        this.score = score;
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
