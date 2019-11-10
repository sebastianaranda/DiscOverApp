package com.digitalhouse.a0819cpmoacn02armo_01.model;

import java.io.Serializable;
import java.net.URL;

public class Genre implements Serializable {

    private int id;
    private String name;
    private URL picture;
    private URL picture_big;

    public Genre(int id, String name, URL picture, URL picture_big) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.picture_big = picture_big;
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

    public URL getPicture_big() {
        return picture_big;
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

    public void setPicture_big(URL picture_big) {
        this.picture_big = picture_big;
    }
}
