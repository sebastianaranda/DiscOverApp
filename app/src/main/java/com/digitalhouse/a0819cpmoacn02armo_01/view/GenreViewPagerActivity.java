package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class GenreViewPagerActivity extends AppCompatActivity {

    public static final String GENRE_LIST_KEY = "genreListKey";
    public static final String GENRE_INDEX_KEY = "genreIndexKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_view_pager);

        Bundle bundle = getIntent().getExtras();
        List<Genre> genreList = (List<Genre>) bundle.getSerializable(GENRE_LIST_KEY);
        int position = bundle.getInt(GENRE_INDEX_KEY);
        ViewPager viewPager = findViewById(R.id.genre_viewpager);
        if (genreList != null && !genreList.isEmpty()) {
            GenreViewPagerAdapter adapter = new GenreViewPagerAdapter(getSupportFragmentManager(), genreList);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(position);
        }
    }

}
