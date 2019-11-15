package com.digitalhouse.a0819cpmoacn02armo_01.controller;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;
import com.digitalhouse.a0819cpmoacn02armo_01.model.AlbumDao;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;

import java.util.List;

public class AlbumsController {

    public void getAlbumsByArtist(Artist artist, final ResultListener<List<Album>> viewListener) {
        AlbumDao albumDao = new AlbumDao();
        albumDao.getAllAlbumsByArtist(artist, new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> result) {
                viewListener.finish(result);
            }
        });
    }

}
