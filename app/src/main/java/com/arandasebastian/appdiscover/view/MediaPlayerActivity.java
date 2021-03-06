package com.arandasebastian.appdiscover.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.arandasebastian.appdiscover.R;

public class MediaPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        Bundle bundle = getIntent().getExtras();

        MediaPlayerFragment mediaPlayerFragment = new MediaPlayerFragment();
        mediaPlayerFragment.setArguments(bundle);
        attachMediaPlayerFragment(mediaPlayerFragment);
    }

    private void attachMediaPlayerFragment(Fragment fragment){
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.media_player_fragment_container, fragment)
            .commit();
    }

}
