package com.arandasebastian.discover.model;

import java.util.ArrayList;

public class FavTracks {

    private ArrayList<Track> tracksList;

    public FavTracks() {
    }

    public FavTracks(ArrayList<Track> tracksList) {
        this.tracksList = tracksList;
    }

    public ArrayList<Track> getTracksList() {
        return tracksList;
    }

    public void setTracksList(ArrayList<Track> tracksList) {
        this.tracksList = tracksList;
    }
}
