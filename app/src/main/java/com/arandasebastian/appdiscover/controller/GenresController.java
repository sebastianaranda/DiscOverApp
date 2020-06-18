package com.arandasebastian.discover.controller;

import com.arandasebastian.discover.ResultListener;
import com.arandasebastian.discover.model.Genre;
import com.arandasebastian.discover.model.GenreDao;
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
