package com.digitalhouse.a0819cpmoacn02armo_01.controller;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.ArtistDao;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;

import java.util.List;

public class ArtistsController {

    public void getArtistsFromDao(final ResultListener<List<Artist>> viewListener) {
        ArtistDao artistDao = new ArtistDao();
        artistDao.getArtistsFromApi(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> result) {
                viewListener.finish(result);
            }
        });
    }

    public void getArtistsByGenre(Genre genre, final ResultListener<List<Artist>> viewListener) {
        ArtistDao artistDao = new ArtistDao();
        artistDao.getAllArtistFromGenre(genre, new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> result) {
                viewListener.finish(result);
            }
        });
    }

    public void getArtistByID(final ResultListener<Artist> viewListener, Integer idArtist){
        ArtistDao artistDao = new ArtistDao();
        artistDao.getArtistByID(new ResultListener<Artist>() {
            @Override
            public void finish(Artist result) {
                viewListener.finish(result);
            }
        }, idArtist);
    }

    public void getArtistTracksByName(final ResultListener<List<Track>> viewListener, String query) {
        ArtistDao artistDao = new ArtistDao();
        artistDao.getArtistTracksByName(new ResultListener<List<Track>>() {
            @Override
            public void finish(List<Track> result) {
                viewListener.finish(result);
            }
        }, query);
    }

}
