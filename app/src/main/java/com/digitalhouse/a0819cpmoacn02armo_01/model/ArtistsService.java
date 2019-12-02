package com.digitalhouse.a0819cpmoacn02armo_01.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArtistsService {

    @GET("/genre/0/artists")
    Call<ContainerArtists> getAllArtists();

    @GET("/genre/{id}/artists")
    Call<ContainerArtists> getAllArtistFromGenre(@Path("id") int idGenre);

    @GET("/artist/{id}")
    Call<Artist> getArtistByID(@Path("id") int idArtist);

}
