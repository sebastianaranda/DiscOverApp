package com.arandasebastian.discover.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Album implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("nb_tracks")
    private int nbTracks;
    @SerializedName("cover_medium")
    private String coverMedium;
    @SerializedName("tracks")
    private ContainerTracks tracks;
    @SerializedName("artist")
    private Artist artist;
    @SerializedName("release_date")
    private Date releaseDate;
    @SerializedName("duration")
    private int duration;

    public Album() {
    }

    public Album(int id, String title, int nbTracks, String coverMedium, ContainerTracks tracks, Artist artist, Date releaseDate, int duration) {
        this.id = id;
        this.title = title;
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

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    public ContainerTracks getTracks() {
        return tracks;
    }

    public void setTracks(ContainerTracks tracks) {
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Date getReleaseDate() {
        return releaseDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) &&
                Objects.equals(artist, album.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist);
    }
}
