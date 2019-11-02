package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.digitalhouse.a0819cpmoacn02armo_01.R;

public class ArtistProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArtistProfileFragment artistProfileFragment = new ArtistProfileFragment();
        artistProfileFragment.setArguments(bundle);
        attachFragment(artistProfileFragment);
    }

    private void attachFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.artist_activity_fragmentContainer,fragment)
                .commit();
    }

}
