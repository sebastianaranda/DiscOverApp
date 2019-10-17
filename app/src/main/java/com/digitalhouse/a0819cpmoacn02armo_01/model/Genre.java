package com.digitalhouse.a0819cpmoacn02armo_01.model;

import java.io.Serializable;
import java.net.URL;

public class Genre implements Serializable {

    private int id;
    private String name;
    private int picture;

    public Genre(int id, String name, int picture) {
        this.id = id;
        this.name = name;
        //TODO: (Juan) Agregar al constructor cuando venga por API y migrar tipo a URL
        this.picture = picture;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

}
