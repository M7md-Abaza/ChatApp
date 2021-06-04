package com.m7mdabaza.chatapp.pojo;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstScreenResponse {

    @SerializedName("Favorites")
    @Expose
    private ArrayList<Favorite> favorites;

    @SerializedName("Recent")
    @Expose
    private ArrayList<Recent> recent;


    public ArrayList<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Favorite> favorites) {
        this.favorites = favorites;
    }

    public ArrayList<Recent> getRecent() {
        return recent;
    }

    public void setRecent(ArrayList<Recent> recent) {
        this.recent = recent;
    }
}
