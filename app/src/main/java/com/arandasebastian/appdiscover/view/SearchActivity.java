package com.arandasebastian.appdiscover.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.arandasebastian.appdiscover.R;
import com.arandasebastian.appdiscover.model.Track;

import java.io.Serializable;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchFragment.FragmentSearchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar_search_activity);
        ImageView imgLogo = findViewById(R.id.toolbar_logo);
        imgLogo.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        attachTrackFragment(new SearchFragment());
    }

    private void attachTrackFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.search_fragment_container, fragment)
                .commit();
    }

    @Override
    public void getTrackFromSearchFragment(Track track, List<Track> customTracklist, boolean fromSearch) {
        Intent intent = new Intent(SearchActivity.this, MediaPlayerActivity.class);
        Bundle bundle = new Bundle();
        if (fromSearch) {
            customTracklist.clear();
        }
        bundle.putSerializable(MediaPlayerFragment.KEY_TRACK, track);
        bundle.putSerializable(MediaPlayerFragment.KEY_TRACKLIST, (Serializable) customTracklist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
