package com.arandasebastian.appdiscover.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.arandasebastian.appdiscover.R;
import com.arandasebastian.appdiscover.model.Album;

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
        bundle.putSerializable(TrackRecyclerFragment.TRACKLIST_ALBUM_KEY, album);
        bundle.putSerializable(AlbumDetailFragment.DETAIL_ALBUM_KEY, album);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
