package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

public class MainActivity extends AppCompatActivity implements ArtistsFragment.FragmentArtistsListener, GenresFragment.GenresFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachFragment(new ArtistsFragment());
        attachGenreFragment(new GenresFragment());
    }

    private void attachFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit();
    }

    private void attachGenreFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.genre_fragment_container, fragment)
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void getArtistFromFragment(Artist artist) {
        //TODO: (Seba) Agregar comportamiento para abrir detalle
        ArtistProfileFragment artistProfileFragment = new ArtistProfileFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable(ArtistProfileFragment.CLAVE_ARTISTA, artist);

        artistProfileFragment.setArguments(bundle);

        attachFragment(artistProfileFragment);
    }

    @Override
    public void getGenreFromFragment(Genre genre) {

    }

}
