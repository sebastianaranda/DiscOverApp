package com.arandasebastian.appdiscover.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerAlbums {

    @SerializedName("data")
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
