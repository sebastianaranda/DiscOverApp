package com.arandasebastian.appdiscover.model;

import java.util.ArrayList;

public class FavAlbum {

    private ArrayList<Album> albumsList;

    public FavAlbum() {
    }

    public FavAlbum(ArrayList<Album> albumsList) {
        this.albumsList = albumsList;
    }

    public ArrayList<Album> getAlbumsList() {
        return albumsList;
    }

    public void setAlbumsList(ArrayList<Album> albumsList) {
        this.albumsList = albumsList;
    }
}
