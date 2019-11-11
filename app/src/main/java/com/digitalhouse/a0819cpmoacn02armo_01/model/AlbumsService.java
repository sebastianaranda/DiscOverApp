package com.digitalhouse.a0819cpmoacn02armo_01.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumsService {

    @GET("/artist/{id}/albums")
    Call<ContainerAlbums> getAllAlbumsByArtist(@Path("id") int idArtist);

}
