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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyFavoritesActivity extends AppCompatActivity implements FavoritesArtistsFragment.FragmentFavoritesArtistsListener, FavoritesAlbumsFragment.FragmentFavoritesAlbumsListener {

    public static final String KEY_FAVORITE = "keyFavorite";
    public static final String KEY_ARTIST_FAV_SELECTED = "Artist";
    public static final String KEY_ALBUM_FAV_SELECTED = "Album";
    private Toolbar toolbar;
    private String favoriteSelected;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar_my_favourites_activity);
        toolbar.setElevation(10);

        if (currentUser == null){
            startActivity(new Intent(MyFavoritesActivity.this,LoginActivity.class));
        } else {
            favoriteSelected = getIntent().getExtras().getSerializable(KEY_FAVORITE).toString();

            switch (favoriteSelected){
                case KEY_ARTIST_FAV_SELECTED:
                    toolbar.setTitle(R.string.txt_toolbar_title_fav_artist);
                    attachFavouritesFragment(new FavoritesArtistsFragment());
                    break;
                case KEY_ALBUM_FAV_SELECTED:
                    toolbar.setTitle(R.string.txt_toolbar_title_fav_album);
                    attachFavouritesFragment(new FavoritesAlbumsFragment());

                    break;
            }
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
        bundle.putSerializable(TrackRecyclerFragment.TRACKLIST_ALBUM_KEY, album);
        bundle.putSerializable(AlbumDetailFragment.DETAIL_ALBUM_KEY, album);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}