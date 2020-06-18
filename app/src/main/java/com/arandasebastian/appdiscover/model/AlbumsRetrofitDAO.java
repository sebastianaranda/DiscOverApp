package com.arandasebastian.appdiscover.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsRetrofitDAO {

    private Retrofit retrofit;
    protected AlbumsService albumsService;

    public AlbumsRetrofitDAO(String baseUrl){
        retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        albumsService = retrofit.create(AlbumsService.class);
    }

}
