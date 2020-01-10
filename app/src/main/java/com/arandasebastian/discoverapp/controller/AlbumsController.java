package com.arandasebastian.discoverapp.controller;

import com.arandasebastian.discoverapp.ResultListener;
import com.arandasebastian.discoverapp.model.Album;
import com.arandasebastian.discoverapp.model.AlbumDao;
import com.arandasebastian.discoverapp.model.Artist;
import com.arandasebastian.discoverapp.model.Track;

import java.util.List;

public class AlbumsController {

    public void getAlbumById(int id, final ResultListener<Album> viewListener) {
        AlbumDao albumDao = new AlbumDao();
        albumDao.getAlbumById(id, new ResultListener<Album>() {
            @Override
            public void finish(Album result) {
                viewListener.finish(result);
            }
        });
    }

    public void getAlbumsByArtist(Artist artist, final ResultListener<List<Album>> viewListener) {
        AlbumDao albumDao = new AlbumDao();
        albumDao.getAllAlbumsByArtist(artist, new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> result) {
                viewListener.finish(result);
            }
        });
    }

    public void getTracklistByAlbum(Album album, final ResultListener<List<Track>> viewListener) {
        AlbumDao albumDao = new AlbumDao();
        albumDao.getAllTracksByAlbum(album, new ResultListener<List<Track>>() {
            @Override
            public void finish(List<Track> result) {
                viewListener.finish(result);
            }
        });
    }

}
