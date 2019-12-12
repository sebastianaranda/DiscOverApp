package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;

public class MyFavoritesActivity extends AppCompatActivity implements FavoritesArtistsFragment.FragmentFavoritesArtistsListener, FavoritesAlbumsFragment.FragmentFavoritesAlbumsListener {

    public static final String KEY_FAVORITE = "keyFavorite";
    private Toolbar toolbar;
    private String favoriteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        toolbar = findViewById(R.id.toolbar_my_favourites_activity);
        toolbar.setElevation(10);

        favoriteSelected = getIntent().getExtras().getSerializable(KEY_FAVORITE).toString();

        switch (favoriteSelected){
            case "Artist":
                toolbar.setTitle("Tus artistas favoritos");
                attachFavouritesFragment(new FavoritesArtistsFragment());
                break;
            case "Album":
                toolbar.setTitle("Tus albums favoritos");
                attachFavouritesFragment(new FavoritesAlbumsFragment());

                break;
            case "Tracks":
                toolbar.setTitle("Tus canciones favoritas");

                break;
        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    private void attachFavouritesFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.my_favourites_activity_fragment_container, fragment)
                .commit();
    }

    @Override
    public void getFavoriteArtistFromFragment(Artist artist) {
        Intent intent = new Intent(MyFavoritesActivity.this, ArtistProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ArtistProfileFragment.KEY_ARTIST, artist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void getFavoriteAlbumFromFragment(Album album) {
        Intent intent = new Intent(MyFavoritesActivity.this, AlbumDetailActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putSerializable(TrackRecyclerFragment.TRACKLIST_ALBUM_KEY, album);
        bundle.putSerializable(AlbumDetailFragment.DETAIL_ALBUM_KEY, album);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}