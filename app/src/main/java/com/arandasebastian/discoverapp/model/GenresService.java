package com.digitalhouse.a0819cpmoacn02armo_01.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GenresService {

    @GET("/genre")
    Call<ContainerGenres> getGenres();

}
