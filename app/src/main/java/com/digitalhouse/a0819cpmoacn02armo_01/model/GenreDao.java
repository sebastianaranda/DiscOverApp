package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class GenreDao {

    public void getGenresFromApi(ResultListener<List<Genre>> controllerListener) {
        List<Genre> genres = new ArrayList<>();
        //TODO: (Juan) Borrar cuando venga la data de la API
        genres.add(new Genre(0, "Rock", R.drawable.ic_artista_placeholder));
        genres.add(new Genre(0, "Pop", R.drawable.ic_artista_placeholder));
        genres.add(new Genre(0, "Jazz", R.drawable.ic_artista_placeholder));
        genres.add(new Genre(0, "J-Pop", R.drawable.ic_artista_placeholder));
        genres.add(new Genre(0, "Metal", R.drawable.ic_artista_placeholder));
        genres.add(new Genre(0, "Punk", R.drawable.ic_artista_placeholder));
        genres.add(new Genre(0, "Trip-Hop", R.drawable.ic_artista_placeholder));
        controllerListener.finish(genres);
    }

}