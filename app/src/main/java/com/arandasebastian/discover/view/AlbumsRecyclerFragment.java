package com.arandasebastian.discover.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arandasebastian.discover.R;
import com.arandasebastian.discover.ResultListener;
import com.arandasebastian.discover.controller.AlbumsController;
import com.arandasebastian.discover.model.Album;
import com.arandasebastian.discover.model.Artist;

import java.util.List;

public class AlbumsRecyclerFragment extends Fragment implements AlbumAdapter.AlbumAdapterListener {

    private FragmentAlbumsListener fragmentAlbumsListener;
    public static final String KEY_ARTIST_PROFILE = "keyArtistProfile";

    public AlbumsRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentAlbumsListener = (FragmentAlbumsListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_albums, container, false);
        Bundle bundle = getArguments();
        Artist artist = (Artist) bundle.getSerializable(KEY_ARTIST_PROFILE);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_albums_recycler);
        final AlbumAdapter albumAdapter = new AlbumAdapter(this);
        AlbumsController albumsController = new AlbumsController();
        albumsController.getAlbumsByArtist(artist, new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> result) {
                albumAdapter.setAlbums(result);
                albumAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(albumAdapter);
        return view;
    }

    @Override
    public void getAlbumFromAdapter(Album album) {
        fragmentAlbumsListener.geAlbumFromFragment(album);
    }

    public interface FragmentAlbumsListener {
        void geAlbumFromFragment(Album album);
    }

}
