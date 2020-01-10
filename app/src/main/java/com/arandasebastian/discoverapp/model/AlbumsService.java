package com.arandasebastian.discoverapp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumsService {

    @GET("/album/{id}")
    Call<Album> getAlbumById(@Path("id") int idAlbum);

    @GET("/artist/{id}/albums")
    Call<ContainerAlbums> getAllAlbumsByArtist(@Path("id") Integer idArtist);

    @GET("/album/{id}/tracks")
    Call<ContainerTracks> getAllTracksByAlbum(@Path("id") int idAlbum);

}
