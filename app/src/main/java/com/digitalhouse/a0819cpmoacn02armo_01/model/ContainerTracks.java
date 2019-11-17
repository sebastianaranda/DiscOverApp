package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerTracks {

    @SerializedName("data")
    private List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

}
