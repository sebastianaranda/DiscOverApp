package com.arandasebastian.discoverapp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArtistsService {

    @GET("/genre/0/artists")
    Call<ContainerArtists> getAllArtists();

    @GET("/genre/{id}/artists")
    Call<ContainerArtists> getAllArtistFromGenre(@Path("id") int idGenre);

    @GET("/artist/{id}")
    Call<Artist> getArtistByID(@Path("id") Integer idArtist);

    @GET("/search")
    Call<ContainerTracks> getArtistTracksByName(@Query("q") String query);

}
