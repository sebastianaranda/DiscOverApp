package com.arandasebastian.appdiscover.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.arandasebastian.appdiscover.R;
import com.arandasebastian.appdiscover.model.Artist;
import com.arandasebastian.appdiscover.model.Genre;
import com.arandasebastian.appdiscover.model.User;
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
    private MenuItem btnLogout;
    private ProgressBar progressBar;

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

        btnLogout = navigationView.getMenu().findItem(R.id.main_menu_logout);
        btnLogout.setEnabled(false);

        progressBar = navigationView.getHeaderView(0).findViewById(R.id.header_navigation_progressbar);
        showProgressBar();
        headerUserName = navigationView.getHeaderView(0).findViewById(R.id.header_user_name);
        headerImageUser = navigationView.getHeaderView(0).findViewById(R.id.header_user_profile_image);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        if (currentUser!=null){
            getCurrentUser();
        } else {
            clearProgressBar();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        if (currentUser!=null){
            getCurrentUser();
        } else {
            clearProgressBar();
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
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
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
            case R.id.main_menu_fav_artists:
                goToFavorites(getString(R.string.txt_navigation_items_favorite_artist_option));
                break;
            case R.id.main_menu_fav_albums:
                goToFavorites(getString(R.string.txt_navigation_items_favorite_album_option));
                break;
            case R.id.main_menu_profile:
                if (currentUser != null){
                    startActivity(new Intent(MainActivity.this,UserProfileActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
                break;
            case R.id.main_menu_logout:
                makeLogout();
                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }

    private void goToFavorites(String itemFavSelected){
        Intent intent = new Intent(MainActivity.this,MyFavoritesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MyFavoritesActivity.KEY_FAVORITE,itemFavSelected);
        intent.putExtras(bundle);
        startActivity(intent);
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
                        if (user != null) {
                            String username = user.getName();
                            if (username == null) {
                                headerUserName.setText(user.getEmail());
                            } else {
                                headerUserName.setText(user.getName());
                            }
                            if (user.getUserProfileImage() != null) {
                                Glide.with(MainActivity.this)
                                    .load(user.getUserProfileImage())
                                        .placeholder(R.drawable.img_artist_placeholder)
                                        .addListener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                clearProgressBar();
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                clearProgressBar();
                                                return false;
                                            }
                                        })
                                    .into(headerImageUser);
                            } else {
                                clearProgressBar();
                            }
                        }
                    }
                });
        btnLogout.setEnabled(true);
    }

    private void clearProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

}