package com.arandasebastian.appdiscover.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrackService {

    @GET("/track/{id}")
    Call<Track> getTrackByID(@Path("id") Integer idTrack);
}
