package com.example.appprojectsfetcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RepositoriesList {
    @SerializedName("repositoriesList")
    private ArrayList<Repository> repositoryArrayList;

    public ArrayList<Repository> getRepositoryArrayList() {
        return repositoryArrayList;
    }

    public void setRepositoryArrayList(ArrayList<Repository> repositoryArrayList) {
        this.repositoryArrayList = repositoryArrayList;
    }
}
