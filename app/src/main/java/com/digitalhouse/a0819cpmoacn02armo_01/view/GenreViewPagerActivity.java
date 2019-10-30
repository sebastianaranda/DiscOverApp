package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class GenreViewPagerActivity extends AppCompatActivity implements ArtistsFragment.FragmentArtistsListener, GenresFragment.GenresFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_view_pager_activity);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Genre> genreList = (List<Genre>) bundle.getSerializable("lista");
        List<Fragment> listaFragment = new ArrayList<>();
        ViewPager viewPager = findViewById(R.id.genre_viewpager);
        for (Genre genre : genreList) {
            listaFragment.add(GenreViewPagerFragment.getFragmentForPager(genre));
        }
        AdapterViewPagerGenre adapter = new AdapterViewPagerGenre(getSupportFragmentManager(), listaFragment);
        viewPager.setAdapter(adapter);

        attachArtistFragment(new ArtistsFragment());
    }

    private void attachArtistFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.artist_fragment_container, fragment)
                .commit();
    }

    @Override
    public void getArtistFromFragment(Artist artist) {
//        Intent intent = new Intent(GenreViewPagerActivity.this, ArtistProfileActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(ArtistProfileFragment.KEY_ARTIST, artist);
//        intent.putExtras(bundle);
//        startActivity(intent);
    }

    @Override
    public void getGenreFromFragment(List<Genre> genre, Integer pos) {

    }
}
