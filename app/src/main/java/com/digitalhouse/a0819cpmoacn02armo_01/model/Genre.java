package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;

public class Genre implements Serializable {

    private int id;
    private String name;
    private URL picture;
    @SerializedName("picture_big")
    private URL pictureBig;

    public Genre(int id, String name, URL picture, URL pictureBig) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.pictureBig = pictureBig;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public URL getPictureBig() {
        return pictureBig;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public void setPictureBig(URL pictureBig) {
        this.pictureBig = pictureBig;
    }
}
