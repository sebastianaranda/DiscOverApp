package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;

public class AlbumDetailActivity extends AppCompatActivity implements TrackRecyclerFragment.FragmentAlbumDetailListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        Bundle bundle = getIntent().getExtras();

        TrackRecyclerFragment trackRecyclerFragment = new TrackRecyclerFragment();
        trackRecyclerFragment.setArguments(bundle);
        attachAlbumFragment(trackRecyclerFragment);

        AlbumDetailFragment albumDetailFragment = new AlbumDetailFragment();
        albumDetailFragment.setArguments(bundle);
        attachTrueAlbumFragment(albumDetailFragment);
    }

    private void attachAlbumFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.tracklist_fragment_container, fragment)
                .commit();
    }

    private void attachTrueAlbumFragment(Fragment fragment){
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.album_activity_fragment_container, fragment)
            .commit();
    }

    @Override
    public void getTrackFromFragment() {

    }

}
