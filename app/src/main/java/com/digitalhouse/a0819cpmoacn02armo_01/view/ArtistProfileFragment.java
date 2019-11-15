package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.AlbumsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

public class ArtistProfileFragment extends Fragment implements AlbumAdapter.AlbumAdapterListener {

    public static final String KEY_ARTIST = "keyArtist";
    private AlbumsRecyclerFragment.FragmentAlbumsListener fragmentAlbumsListener;
    ProgressBar progressBar;

    public ArtistProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentAlbumsListener = (AlbumsRecyclerFragment.FragmentAlbumsListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_artist_profile, container, false);

        ImageView imgArtistPicture = fragmentView.findViewById(R.id.img_artist_picture);
        //TODO: chequear si podemos pedir este dato a la API y modificar este codigo
        TextView txtArtistFans = fragmentView.findViewById(R.id.txt_artist_fans);
        CollapsingToolbarLayout collapsingToolbarLayoutTitle = fragmentView.findViewById(R.id.artist_profile_collapsing_toolbar_layout);

        progressBar = fragmentView.findViewById(R.id.progress_bar_profile);

        Bundle bundle = getArguments();
        Artist selectedArtist = (Artist) bundle.getSerializable(KEY_ARTIST);

        Glide.with(fragmentView)
                .load(selectedArtist.getPictureBig())
                .placeholder(R.drawable.img_artist_placeholder)
                .into(imgArtistPicture);

        collapsingToolbarLayoutTitle.setTitle(selectedArtist.getName());
        collapsingToolbarLayoutTitle.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        collapsingToolbarLayoutTitle.setCollapsedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        RecyclerView recyclerView = fragmentView.findViewById(R.id.fragment_albums_recycler);
        final AlbumAdapter albumAdapter = new AlbumAdapter(this);
        recyclerView.setAdapter(albumAdapter);
        progressBar.setVisibility(View.VISIBLE);
        AlbumsController albumsController = new AlbumsController();
        albumsController.getAlbumsByArtist(selectedArtist, new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> result) {
                albumAdapter.setAlbums(result);
                albumAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });

        return fragmentView;
    }

    @Override
    public void getAlbumFromAdapter(Album album) {
        fragmentAlbumsListener.geAlbumFromFragment(album);
    }

}
