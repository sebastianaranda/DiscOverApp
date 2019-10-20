package com.digitalhouse.a0819cpmoacn02armo_01.controller;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import com.digitalhouse.a0819cpmoacn02armo_01.model.GenreDao;
import java.util.List;

public class GenresController {

    public void getGenresFromDao(final ResultListener<List<Genre>> viewListener) {
        GenreDao genreDao = new GenreDao();
        genreDao.getGenresFromApi(new ResultListener<List<Genre>>() {
            @Override
            public void finish(List<Genre> result) {
                viewListener.finish(result);
            }
        });
    }

}
