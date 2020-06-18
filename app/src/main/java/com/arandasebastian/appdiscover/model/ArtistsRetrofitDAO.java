package com.arandasebastian.appdiscover.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistsRetrofitDAO {

    private Retrofit retrofit;
    protected ArtistsService artistsService;

    public ArtistsRetrofitDAO(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        artistsService = retrofit.create(ArtistsService.class);
    }
}
