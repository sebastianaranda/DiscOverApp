package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ArtistaDao {

    public void traerArtistas(ResultListener<List<Artista>> listenerDelController) {
        List<Artista> artistas = new ArrayList<>();
        //TODO: (Juan) Borrar cuando venga la data de la API
        artistas.add(new Artista(0, "Mudvayne", 4));
        artistas.add(new Artista(0, "Tool", 4));
        artistas.add(new Artista(0, "Kidney Thieves", 4));
        artistas.add(new Artista(0, "The Bangles", 7));
        artistas.add(new Artista(0, "Black Sabbath", 13));
        artistas.add(new Artista(0, "Dir en Grey", 12));
        artistas.add(new Artista(0, "L'arc en ciel", 9));
        artistas.add(new Artista(0, "Tori Amos", 5));
        artistas.add(new Artista(0, "Judas Priest", 15));
        artistas.add(new Artista(0, "Portishead", 3));
        artistas.add(new Artista(0, "Jack Off Jill", 2));
        artistas.add(new Artista(0, "King Crimson", 8));
        artistas.add(new Artista(0, "Jinjer", 3));
        artistas.add(new Artista(0, "Betty Davies", 10));

        listenerDelController.finish(artistas);
    }
}
