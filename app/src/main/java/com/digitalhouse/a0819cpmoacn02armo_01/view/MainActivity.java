package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import com.facebook.AccessToken;
import com.google.android.material.navigation.NavigationView;
import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArtistsRecyclerFragment.FragmentArtistsListener, GenresRecyclerFragment.GenresFragmentListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.main_activity_drawer_layout);
        NavigationView navigationView = findViewById(R.id.main_activity_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.txt_drawer_open,R.string.txt_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        attachArtistFragment(new ArtistsRecyclerFragment());
        attachGenreFragment(new GenresRecyclerFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemMenuSelected = item.getItemId();
        switch (itemMenuSelected){
            case R.id.toolbar_menu_search:
                //TODO: modificar esta linea una vez definido el metodo de search
                Toast.makeText(this, "Seleccionaste la opcion para search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_menu_settings:
                //TODO: modificar esta linea una vez definido el metodo de settings
                Toast.makeText(this, "Seleccionaste la opcion de settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void attachArtistFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.artist_fragment_container, fragment)
            .commit();
    }

    private void attachGenreFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.genre_fragment_container, fragment)
            .commit();
    }

    @Override
    public void getArtistFromFragment(Artist artist) {
        Intent intent = new Intent(MainActivity.this, ArtistProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ArtistProfileFragment.KEY_ARTIST, artist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void getGenreFromFragment(List<Genre> genreList, Integer pos) {
        Intent intent = new Intent(MainActivity.this, GenreViewPagerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(GenreViewPagerActivity.GENRE_LIST_KEY, (Serializable) genreList);
        bundle.putInt(GenreViewPagerActivity.GENRE_INDEX_KEY, pos);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Integer id = menuItem.getItemId();
        switch (id){
            //TODO: Definir comportamiento del menu y borrar toast
            case R.id.main_menu_fav_artists:
                Toast.makeText(this, "Seleccionaste Artistas favoritos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_fav_albums:
                Toast.makeText(this, "Seleccionaste Albumes favoritos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_fav_songs:
                Toast.makeText(this, "Seleccionaste Canciones favoritas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_profile:
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken!= null && !accessToken.isExpired()){
                    Intent intent = new Intent(MainActivity.this,UserProfileActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.main_menu_settings:
                Toast.makeText(this, "Seleccionaste el menu Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_logout:
                Toast.makeText(this, "Seleccionaste el menu Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }

}
