package com.digitalhouse.a0819cpmoacn02armo_01.model;

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
