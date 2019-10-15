package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;

public class MainActivity extends AppCompatActivity implements ArtistsFragment.FragmentArtistsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachFragment(new ArtistsFragment());
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
        ArtistProfileFragment artistProfileFragment = new ArtistProfileFragment();
        Intent intent = new Intent(MainActivity.this,ArtistActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ArtistProfileFragment.CLAVE_ARTISTA,artist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
