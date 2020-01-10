package com.arandasebastian.discoverapp.controller;

import com.arandasebastian.discoverapp.ResultListener;
import com.arandasebastian.discoverapp.model.Artist;
import com.arandasebastian.discoverapp.model.ArtistDao;
import com.arandasebastian.discoverapp.model.Genre;
import com.arandasebastian.discoverapp.model.Track;

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
