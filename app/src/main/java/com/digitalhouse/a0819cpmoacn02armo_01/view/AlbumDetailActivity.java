package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;

public class AlbumDetailActivity extends AppCompatActivity implements AlbumDetailFragment.FragmentAlbumDetailListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        Bundle bundle = getIntent().getExtras();

        AlbumDetailFragment albumDetailFragment = new AlbumDetailFragment();
        albumDetailFragment.setArguments(bundle);
        attachAlbumFragment(albumDetailFragment);
    }

    private void attachAlbumFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.album_activity_fragment_container, fragment)
                .commit();
    }

    @Override
    public void getTrackFromFragment() {

    }
}
