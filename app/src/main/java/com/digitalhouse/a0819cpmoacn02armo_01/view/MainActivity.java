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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import com.digitalhouse.a0819cpmoacn02armo_01.model.User;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.Serializable;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements ArtistsRecyclerFragment.FragmentArtistsListener, GenresRecyclerFragment.GenresFragmentListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String COLLECTION_USERS = "Users";
    private DrawerLayout drawerLayout;
    private TextView headerUserName;
    private CircleImageView headerImageUser;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.main_activity_drawer_layout);
        NavigationView navigationView = findViewById(R.id.main_activity_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        ImageView imgLogo = findViewById(R.id.toolbar_logo);
        imgLogo.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.txt_drawer_open,R.string.txt_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        attachArtistFragment(new ArtistsRecyclerFragment());
        attachGenreFragment(new GenresRecyclerFragment());

        headerUserName = navigationView.getHeaderView(0).findViewById(R.id.header_user_name);
        headerImageUser = navigationView.getHeaderView(0).findViewById(R.id.header_user_profile_image);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        if (currentUser!=null){
            getCurrentUser();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentUser = auth.getCurrentUser();
        if (currentUser!=null){
            getCurrentUser();
        }
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
                if (currentUser != null){
                    startActivity(new Intent(MainActivity.this,UserProfileActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
                break;
            case R.id.main_menu_settings:
                Toast.makeText(this, "Seleccionaste el menu Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_logout:
                makeLogout();
                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }

    private void makeLogout(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        currentUser = null;
        Toast.makeText(this, R.string.txt_logout, Toast.LENGTH_SHORT).show();
        headerUserName.setText(getString(R.string.txt_header_username));
        Glide.with(MainActivity.this)
                .load(R.drawable.img_artist_placeholder)
                .into(headerImageUser);
    }

    private void getCurrentUser(){
        firestore.collection(COLLECTION_USERS)
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        String username = user.getName();
                        if (username == null){
                            headerUserName.setText(user.getEmail());
                        }else {
                            headerUserName.setText(user.getName());
                        }
                        if (user.getUserProfileImage() != null){
                            Glide.with(MainActivity.this)
                                    .load(user.getUserProfileImage())
                                    .into(headerImageUser);
                        }
                    }
                });
    }

}