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
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;

public class AlbumDetailFragment extends Fragment implements TrackAdapter.TrackAdapterListener {

    private FragmentAlbumDetailListener fragmentAlbumDetailListener;
    public static final String ALBUM_KEY = "albumKey";

    public AlbumDetailFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album_detail, container, false);

        Bundle bundle = getArguments();
        Album album = (Album) bundle.getSerializable(ALBUM_KEY);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_tracks_recycler);
        final TrackAdapter trackAdapter = new TrackAdapter(this);
        if (album != null) {
            trackAdapter.setTrackList(album.getTracks());
            trackAdapter.notifyDataSetChanged();
        }
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
