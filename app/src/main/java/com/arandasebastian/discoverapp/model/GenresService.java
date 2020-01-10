package com.arandasebastian.discoverapp.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GenresService {

    @GET("/genre")
    Call<ContainerGenres> getGenres();

}
