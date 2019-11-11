package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;

public class Album implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("nb_tracks")
    private int nbTracks;
    @SerializedName("cover_medium")
    private URL coverMedium;

    public Album(int id, String title, int nbTracks, URL coverMedium) {
        this.id = id;
        this.title = title;
        //TODO: (Juan) Setear cantidad de temas con un size de la lista de tracks ya que no anda
        this.nbTracks = nbTracks;
        this.coverMedium = coverMedium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNbTracks() {
        return nbTracks;
    }

    public void setNbTracks(int nbTracks) {
        this.nbTracks = nbTracks;
    }

    public URL getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(URL coverMedium) {
        this.coverMedium = coverMedium;
    }

}
