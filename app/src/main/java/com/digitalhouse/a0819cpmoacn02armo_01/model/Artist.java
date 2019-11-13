package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.net.URL;

public class Artist implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("nb_album")
    private int nbAlbum;
    @SerializedName("picture_small")
    private URL pictureSmall;
    @SerializedName("picture_big")
    private URL pictureBig;
    private String yearsActive;
    private String description;
    @SerializedName("nb_fan")
    private int nbFans;

    public Artist(int id, String name, int nbAlbum, URL pictureSmall,URL pictureBig, String yearsActive, String description, int nbFans) {
        this.id = id;
        this.name = name;
        this.nbAlbum = nbAlbum;
        this.yearsActive = yearsActive;
        this.description = description;
        this.pictureSmall = pictureSmall;
        this.pictureBig = pictureBig;
        this.nbFans = nbFans;
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

    public URL getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(URL pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public URL getPictureBig() {
        return pictureBig;
    }

    public void setPictureBig(URL pictureBig) {
        this.pictureBig = pictureBig;
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

    public int getNbFans() {
        return nbFans;
    }

    public void setNbFans(int nbFans) {
        this.nbFans = nbFans;
    }
}
