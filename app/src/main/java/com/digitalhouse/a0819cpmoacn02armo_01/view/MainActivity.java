package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;

public class MainActivity extends AppCompatActivity implements FragmentArtists.FragmentArtistsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachFragment(new FragmentArtists());
    }

    private void attachFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void getArtistFromFragment(Artist artist) {
        //TODO: (Seba) Agregar comportamiento para abrir detalle
        FragmentArtistProfile fragmentArtistProfile = new FragmentArtistProfile();

        Bundle bundle = new Bundle();

        bundle.putSerializable(FragmentArtistProfile.CLAVE_ARTISTA, artist);

        fragmentArtistProfile.setArguments(bundle);

        attachFragment(fragmentArtistProfile);
    }

}
