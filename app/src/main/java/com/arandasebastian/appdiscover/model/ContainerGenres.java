package com.arandasebastian.appdiscover.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerGenres {

    @SerializedName("data")
    private List<Genre> genresList;

    public List<Genre> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<Genre> genresList) {
        this.genresList = genresList;
    }

}
