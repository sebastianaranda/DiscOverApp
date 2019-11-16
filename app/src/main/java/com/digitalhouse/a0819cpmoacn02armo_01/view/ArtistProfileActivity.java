package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;

public class ArtistProfileActivity extends AppCompatActivity implements AlbumsRecyclerFragment.FragmentAlbumsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ArtistProfileFragment artistProfileFragment = new ArtistProfileFragment();
        artistProfileFragment.setArguments(bundle);
        attachProfileFragment(artistProfileFragment);

    }

    private void attachProfileFragment(Fragment fragment){
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.artist_activity_fragmentContainer, fragment)
            .commit();
    }

    @Override
    public void geAlbumFromFragment(Album album) {
        Intent intent = new Intent(ArtistProfileActivity.this, AlbumDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AlbumDetailFragment.ALBUM_KEY, album);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
