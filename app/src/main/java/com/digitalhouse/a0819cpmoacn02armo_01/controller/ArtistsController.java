package com.digitalhouse.a0819cpmoacn02armo_01.controller;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.ArtistDao;

import java.util.List;

public class ArtistsController {

    public List<Artist> getArtistsFromDao(final ResultListener<List<Artist>> viewListener) {
        ArtistDao artistDao = new ArtistDao();
        artistDao.getArtistsFromApi(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> result) {
                viewListener.finish(result);
            }
        });
        return null;
    }

}
