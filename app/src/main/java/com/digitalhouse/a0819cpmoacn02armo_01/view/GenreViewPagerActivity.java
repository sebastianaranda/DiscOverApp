package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class GenreViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_view_pager_activity);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
//        List<Genre> genreList = bundle.getSerializable("lista");
        ViewPager viewPager = findViewById(R.id.genre_viewpager);
//        AdapterViewPagerGenre adapter = new AdapterViewPagerGenre(getSupportFragmentManager(), genreList);
//        viewPager.setAdapter(adapter);

        attachArtistFragment(new ArtistsFragment());
    }

    private void attachArtistFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.artist_fragment_container, fragment)
                .commit();
    }

}
