package com.digitalhouse.a0819cpmoacn02armo_01.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenresRetrofitDAO {

    private Retrofit retrofit;
    protected GenresService genresService;

    public GenresRetrofitDAO(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        genresService = retrofit.create(GenresService.class);
    }

}