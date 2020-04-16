package com.arandasebastian.discover.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

public class Artist implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("nb_album")
    private int nbAlbum;
    @SerializedName("picture_small")
    private String pictureSmall;
    @SerializedName("picture_big")
    private String pictureBig;
    private String yearsActive;
    private String description;
    @SerializedName("nb_fan")
    private int nbFans;

    public Artist(Integer id, String name, int nbAlbum, String pictureSmall, String pictureBig, String yearsActive, String description, int nbFans) {
        this.id = id;
        this.name = name;
        this.nbAlbum = nbAlbum;
        this.yearsActive = yearsActive;
        this.description = description;
        this.pictureSmall = pictureSmall;
        this.pictureBig = pictureBig;
        this.nbFans = nbFans;
    }

    public Artist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(String pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public String getPictureBig() {
        return pictureBig;
    }

    public void setPictureBig(String pictureBig) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) &&
                Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
