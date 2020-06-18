package com.arandasebastian.appdiscover.controller;

import com.arandasebastian.appdiscover.ResultListener;
import com.arandasebastian.appdiscover.model.Genre;
import com.arandasebastian.appdiscover.model.GenreDao;
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
