package com.arandasebastian.discover.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ContainerArtists {

    @SerializedName("data")
    private List<Artist> artistsList;

    public List<Artist> getArtistsList() {
        return artistsList;
    }

    public void setArtistsList(List<Artist> artistsList) {
        this.artistsList = artistsList;
    }
}
