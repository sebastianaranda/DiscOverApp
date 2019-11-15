package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerAlbums {

    @SerializedName("data")
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }
}
