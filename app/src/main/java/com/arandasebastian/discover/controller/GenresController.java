package com.arandasebastian.discoverapp.controller;

import com.arandasebastian.discoverapp.ResultListener;
import com.arandasebastian.discoverapp.model.Genre;
import com.arandasebastian.discoverapp.model.GenreDao;
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
