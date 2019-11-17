package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.AlbumsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackRecyclerFragment extends Fragment implements TrackAdapter.TrackAdapterListener {

    private FragmentAlbumDetailListener fragmentAlbumDetailListener;
    public static final String TRACKLIST_ALBUM_KEY = "tracklistAlbumKey";

    public TrackRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentAlbumDetailListener = (FragmentAlbumDetailListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_tracks, container, false);

        Bundle bundle = getArguments();
        final Album album = (Album) bundle.getSerializable(TRACKLIST_ALBUM_KEY);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_tracks_recycler);
        final TrackAdapter trackAdapter = new TrackAdapter(this);
        AlbumsController albumsController = new AlbumsController();
        albumsController.getTracklistByAlbum(album, new ResultListener<List<Track>>() {
            @Override
            public void finish(List<Track> result) {
                List<Track> customizedTrackList = new ArrayList<>();
                for (Track track : result) {
                    track.setCoverMedium(album.getCoverMedium());
                    customizedTrackList.add(track);
                }
                trackAdapter.setTrackList(customizedTrackList);
                trackAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(trackAdapter);
        return view;
    }

    @Override
    public void getTrackFromAdapter(Track track) {
        fragmentAlbumDetailListener.getTrackFromFragment();
    }

    public interface FragmentAlbumDetailListener {
        void getTrackFromFragment();
    }

}
