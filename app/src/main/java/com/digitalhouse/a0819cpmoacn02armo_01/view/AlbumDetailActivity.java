package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;

import java.io.Serializable;
import java.util.List;

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
    public void getTrackFromFragment(Track track, List<Track> customTracklist, boolean fromSearch) {
        Intent intent = new Intent(AlbumDetailActivity.this, MediaPlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MediaPlayerFragment.KEY_TRACK, track);
        bundle.putSerializable(MediaPlayerFragment.KEY_TRACKLIST, (Serializable) customTracklist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
