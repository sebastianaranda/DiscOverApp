package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class Album implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("nb_tracks")
    private int nbTracks;
    @SerializedName("cover_medium")
    private URL coverMedium;
    @SerializedName("tracks")
    private List<Track> tracks;
    @SerializedName("artist")
    private Artist artist;
    @SerializedName("release_date")
    private Date releaseDate;
    @SerializedName("duration")
    private int duration;

    public Album(int id, String title, int nbTracks, URL coverMedium, List<Track> tracks, Artist artist, Date releaseDate, int duration) {
        this.id = id;
        this.title = title;
        //TODO: (Juan) Setear cantidad de temas con un size de la lista de tracks ya que no anda
        this.nbTracks = nbTracks;
        this.coverMedium = coverMedium;
        this.tracks = tracks;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.duration = duration;
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate.toString();
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDurationMinutes() {
        return String.valueOf(getDuration()/60);
    }

}
