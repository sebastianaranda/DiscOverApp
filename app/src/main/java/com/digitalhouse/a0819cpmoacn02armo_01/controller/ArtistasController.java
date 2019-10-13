package com.digitalhouse.a0819cpmoacn02armo_01.controller;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artista;
import com.digitalhouse.a0819cpmoacn02armo_01.model.ArtistaDao;

import java.util.List;

public class ArtistasController {

    public List<Artista> traerArtistas(final ResultListener<List<Artista>> listenerDeVista) {
        ArtistaDao artistaDao = new ArtistaDao();
        artistaDao.traerArtistas(new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> result) {
                listenerDeVista.finish(result);
            }
        });
        return null;
    }

}
