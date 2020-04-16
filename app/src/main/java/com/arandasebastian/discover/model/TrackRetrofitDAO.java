package com.arandasebastian.discover.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackRetrofitDAO {

    private Retrofit retrofit;
    protected TrackService trackService;

    public TrackRetrofitDAO(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        trackService = retrofit.create(TrackService.class);
    }
}
