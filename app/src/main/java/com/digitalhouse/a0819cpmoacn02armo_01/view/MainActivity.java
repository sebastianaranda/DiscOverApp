package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements ArtistsFragment.FragmentArtistsListener, GenresFragment.GenresFragmentListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachArtistFragment(new ArtistsFragment());
        attachGenreFragment(new GenresFragment());  
    }

    private void attachFragment(Fragment fragment) {
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
        Intent intent = new Intent(MainActivity.this, ArtistActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ArtistProfileFragment.KEY_ARTIST,artist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void getGenreFromFragment(Genre genre) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Integer id = menuItem.getItemId();
        switch (id){
            //TODO: Definir comportamiento del menu
            case R.id.mainMenuFav:
                Toast.makeText(this, "Seleccionaste el menu Favoritos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainMenuProfile:
                Toast.makeText(this, "Seleccionaste el menu Perfil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainMenuSettings:
                Toast.makeText(this, "Seleccionaste el menu Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainMenuLogout:
                Toast.makeText(this, "Seleccionaste el menu Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }

}
