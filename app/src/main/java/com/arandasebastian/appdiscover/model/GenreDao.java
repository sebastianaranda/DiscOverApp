package com.arandasebastian.appdiscover.model;

import android.util.Log;

import com.arandasebastian.appdiscover.ResultListener;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreDao extends GenresRetrofitDAO {

    public static final String BASE_URL = "https://api.deezer.com";
    private static final String LOG_TAG = "CALL_GENRE";

    public GenreDao() {
        super(BASE_URL);
    }

    public void getGenresFromApi(final ResultListener<List<Genre>> controllerListener) {
        Call<ContainerGenres>call = genresService.getGenres();
        call.enqueue(new Callback<ContainerGenres>() {
            @Override
            public void onResponse(Call<ContainerGenres> call, Response<ContainerGenres> response) {
                ContainerGenres containerGenres = response.body();
                controllerListener.finish(containerGenres.getGenresList());
            }

            @Override
            public void onFailure(Call<ContainerGenres> call, Throwable t) {
                Log.d(LOG_TAG, "Request failed");
            }
        });
    }

}