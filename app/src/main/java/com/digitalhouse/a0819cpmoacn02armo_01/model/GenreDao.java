package com.digitalhouse.a0819cpmoacn02armo_01.model;

import android.util.Log;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreDao extends GenresRetrofitDAO {
    public static final String BASE_URL = "https://api.deezer.com";

    public GenreDao() {
        super(BASE_URL);
    }

    public void getGenresFromApi(final ResultListener<List<Genre>> controllerListener) {
        Call<ContainerGenres>call = genresService.getsGenres();
        call.enqueue(new Callback<ContainerGenres>() {
            @Override
            public void onResponse(Call<ContainerGenres> call, Response<ContainerGenres> response) {
                ContainerGenres containerGenres = response.body();
                controllerListener.finish(containerGenres.getGenresList());
            }

            @Override
            public void onFailure(Call<ContainerGenres> call, Throwable t) {
                Log.d("asdasd","asdasd");
            }
        });
    }

}