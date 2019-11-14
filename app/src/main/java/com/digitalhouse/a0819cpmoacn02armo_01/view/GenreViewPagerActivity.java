package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.ArtistsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import java.util.List;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class GenreViewPagerActivity extends AppCompatActivity implements ArtistAdapter.ArtistAdapterListener {

    public static final String GENRE_LIST_KEY = "genreListKey";
    public static final String GENRE_INDEX_KEY = "genreIndexKey";
    private GenreViewPagerAdapter viewPagerAdapter;
    private ArtistAdapter artistAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_view_pager);

        Toolbar toolbar = findViewById(R.id.toolbar_genre_viewpager_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = findViewById(R.id.genre_viewpager);
        final RecyclerView recyclerView = findViewById(R.id.fragment_artists_recycler);
        artistAdapter = new ArtistAdapter(GenreViewPagerActivity.this);
        progressBar = findViewById(R.id.progressBar);

        Bundle bundle = getIntent().getExtras();
        List<Genre> genreList = (List<Genre>) bundle.getSerializable(GENRE_LIST_KEY);
        int position = bundle.getInt(GENRE_INDEX_KEY);
        if (genreList != null && !genreList.isEmpty()) {
            viewPagerAdapter = new GenreViewPagerAdapter(getSupportFragmentManager(), genreList);
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setCurrentItem(position);
        }
        Genre selectedGenre = genreList.get(position);
        toolbar.setTitle(selectedGenre.getName());
        recyclerView.setAdapter(artistAdapter);
        displayArtistsByGenre(position);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                displayArtistsByGenre(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void displayArtistsByGenre(int position) {
        progressBar.setVisibility(View.VISIBLE);
        Genre genre = viewPagerAdapter.getGenreFromPosition(position);
        ArtistsController artistsController = new ArtistsController();
        artistsController.getArtistsByGenre(genre, new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> result) {
                artistAdapter.setArtists(result);
                artistAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void getArtistFromAdapter(Artist artist) {
        Intent intent = new Intent(GenreViewPagerActivity.this, ArtistProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ArtistProfileFragment.KEY_ARTIST, artist);
        bundle.putSerializable(AlbumsRecyclerFragment.KEY_ARTIST_PROFILE, artist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
