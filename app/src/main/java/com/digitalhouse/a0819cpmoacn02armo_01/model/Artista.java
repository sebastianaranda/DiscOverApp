package com.digitalhouse.a0819cpmoacn02armo_01.model;

import java.net.URL;

public class Artista {

    private int id;
    private String name;
    private int nbAlbum;
    private URL pictureSmall;

    public Artista(int id, String name, int nbAlbum) {
        this.id = id;
        this.name = name;
        this.nbAlbum = nbAlbum;
        //TODO: (Juan) Descomentar cuando traigamos imagen por URL
        //this.pictureSmall = pictureSmall;
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

}
