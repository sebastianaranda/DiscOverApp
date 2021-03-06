package com.arandasebastian.appdiscover.model;

import android.util.Log;

import com.arandasebastian.appdiscover.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumDao extends AlbumsRetrofitDAO {

    public static final String BASE_URL = "https://api.deezer.com";
    private static final String LOG_TAG = "CALL_ALBUM";

    public AlbumDao() {
        super(BASE_URL);
    }

    public void getAlbumById(int id, final ResultListener<Album> controllerListener) {
        Call<Album> call = albumsService.getAlbumById(id);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Album requestedAlbum = response.body();
                controllerListener.finish(requestedAlbum);
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Log.d(LOG_TAG, "Request failed");
            }
        });
    }

    public void getAllAlbumsByArtist(Artist artist, final ResultListener<List<Album>> controllerListener) {
        Call<ContainerAlbums> call = albumsService.getAllAlbumsByArtist(artist.getId());
        call.enqueue(new Callback<ContainerAlbums>() {
            @Override
            public void onResponse(Call<ContainerAlbums> call, Response<ContainerAlbums> response) {
                ContainerAlbums containerAlbums = response.body();
                controllerListener.finish(containerAlbums.getAlbums());
            }

            @Override
            public void onFailure(Call<ContainerAlbums> call, Throwable t) {
                Log.d(LOG_TAG, "Request failed");
            }
        });
    }

    public void getAllTracksByAlbum(Album album, final ResultListener<List<Track>> controllerListener) {
        Call<ContainerTracks> call = albumsService.getAllTracksByAlbum(album.getId());
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
