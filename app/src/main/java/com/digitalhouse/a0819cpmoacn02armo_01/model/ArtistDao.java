package com.digitalhouse.a0819cpmoacn02armo_01.model;

import android.util.Log;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistDao extends ArtistsRetrofitDAO {
    public static final String BASE_URL = "https://api.deezer.com";
    public static final String LOG_TAG = "CALL_ARTIST";

    public ArtistDao() {
        super(BASE_URL);
    }

    public void getArtistsFromApi(final ResultListener<List<Artist>> controllerListener) {
        Call<ContainerArtists> call = artistsService.getAllArtists();

        call.enqueue(new Callback<ContainerArtists>() {
            @Override
            public void onResponse(Call<ContainerArtists> call, Response<ContainerArtists> response) {
                ContainerArtists containerArtists = response.body();
                controllerListener.finish(containerArtists.getArtistsList());
            }

            @Override
            public void onFailure(Call<ContainerArtists> call, Throwable t) {
                Log.d(LOG_TAG, "Request failed");
            }
        });
    }

    public void getAllArtistFromGenre(Genre genre, final ResultListener<List<Artist>> controllerListener) {
        Call<ContainerArtists> call = artistsService.getAllArtistFromGenre(genre.getId());

        call.enqueue(new Callback<ContainerArtists>() {
            @Override
            public void onResponse(Call<ContainerArtists> call, Response<ContainerArtists> response) {
                ContainerArtists containerArtists = response.body();
                controllerListener.finish(containerArtists.getArtistsList());
            }

            @Override
            public void onFailure(Call<ContainerArtists> call, Throwable t) {
                Log.d(LOG_TAG, "Request failed");
            }
        });
    }

    public void getArtistByID(final ResultListener<Artist> controllerListener, Integer artistID){
        Call<Artist> call = artistsService.getArtistByID(artistID);

        call.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                Artist selectedArtist = response.body();
                controllerListener.finish(selectedArtist);
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.d(LOG_TAG, "Request failed");
            }
        });
    }

    public void getArtistTracksByName(final ResultListener<List<Track>> controllerListener, String query) {
        Call<ContainerTracks> call = artistsService.getArtistTracksByName(query);

        call.enqueue(new Callback<ContainerTracks>() {
            @Override
            public void onResponse(Call<ContainerTracks> call, Response<ContainerTracks> response) {
                ContainerTracks containerTracks = response.body();
                controllerListener.finish(containerTracks.getTrackList());
            }

            @Override
            public void onFailure(Call<ContainerTracks> call, Throwable t) {
                Log.d(LOG_TAG, "Request failed");
            }
        });
    }

}
