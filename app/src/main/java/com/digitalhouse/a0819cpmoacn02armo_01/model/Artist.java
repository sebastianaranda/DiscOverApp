package com.digitalhouse.a0819cpmoacn02armo_01.model;

import java.io.Serializable;

public class Artist implements Serializable {

    private int id;
    private String name;
    private int nbAlbum;
    private Integer pictureSmall;
    private String yearsActive;
    private String description;

    public Artist(int id, String name, int nbAlbum, Integer pictureSmall, String yearsActive, String description) {
        this.id = id;
        this.name = name;
        this.nbAlbum = nbAlbum;
        this.yearsActive = yearsActive;
        this.description = description;

        //TODO: (Juan) Descomentar cuando traigamos imagen por URL
        this.pictureSmall = pictureSmall;
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

    public int getNbAlbum() {
        return nbAlbum;
    }

    public void setNbAlbum(int nbAlbum) {
        this.nbAlbum = nbAlbum;
    }

    public Integer getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(Integer pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public String getYearsActive() {
        return yearsActive;
    }

    public void setYearsActive(String yearsActive) {
        this.yearsActive = yearsActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
