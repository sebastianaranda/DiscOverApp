package com.arandasebastian.discover.model;

import java.util.ArrayList;

public class FavArtist {

    private ArrayList<Artist> artistList;

    public FavArtist() {
    }

    public FavArtist(ArrayList<Artist> artistList) {
        this.artistList = artistList;
    }

    public ArrayList<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(ArrayList<Artist> artistList) {
        this.artistList = artistList;
    }
}
