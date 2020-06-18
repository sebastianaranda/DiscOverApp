package com.arandasebastian.appdiscover.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Track implements Serializable {

    private int id;
    private String title;
    private int duration;
    private String preview;
    private Artist artist;
    private Album album;
    private String coverMedium;
    @SerializedName("track_position")
    private int trackPosition;
    @SerializedName("share")
    private String share;

    public Track() {
    }

    public Track(int id, String title, int duration, String preview, Artist artist, Album album, String share) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.preview = preview;
        this.artist = artist;
        this.album = album;
        this.share = share;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    public int getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id,track.id) &&
                Objects.equals(title,track.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
