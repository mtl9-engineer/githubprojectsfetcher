package com.example.appprojectsfetcher.model;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("login")
    private String login;
    @SerializedName("avatart_url")
    private String avatarUrl;

    public Owner(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
