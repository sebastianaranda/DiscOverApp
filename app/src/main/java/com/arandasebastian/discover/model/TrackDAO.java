package com.arandasebastian.discover.model;

import com.arandasebastian.discover.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackDAO extends TrackRetrofitDAO {
    public static final String BASE_URL = "https://api.deezer.com";

    public TrackDAO() {
        super(BASE_URL);
    }

    public void getTrackByID(final ResultListener<Track> controllerListener, Integer trackID){
        Call<Track> call = trackService.getTrackByID(trackID);

        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                Track selectedTrack = response.body();
                controllerListener.finish(selectedTrack);
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {

            }
        });
    }
}
