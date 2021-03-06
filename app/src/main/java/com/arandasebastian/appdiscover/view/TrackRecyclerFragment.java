package com.arandasebastian.appdiscover.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arandasebastian.appdiscover.R;
import com.arandasebastian.appdiscover.ResultListener;
import com.arandasebastian.appdiscover.controller.AlbumsController;
import com.arandasebastian.appdiscover.controller.NetworkUtils;
import com.arandasebastian.appdiscover.model.Album;
import com.arandasebastian.appdiscover.model.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackRecyclerFragment extends Fragment implements TrackAdapter.TrackAdapterListener {

    private FragmentAlbumDetailListener fragmentAlbumDetailListener;
    public static final String TRACKLIST_ALBUM_KEY = "tracklistAlbumKey";
    private List<Track> customTracklist;
    private boolean fromSearch = false;

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
        View view;
        if (!NetworkUtils.isNetworkAvailable(getContext())) {
            view = inflater.inflate(R.layout.fragment_empty_state, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_recycler_tracks, container, false);

            Bundle bundle = getArguments();
            final Album album = (Album) bundle.getSerializable(TRACKLIST_ALBUM_KEY);

            RecyclerView recyclerView = view.findViewById(R.id.fragment_tracks_recycler);
            final TrackAdapter trackAdapter = new TrackAdapter(this);
            AlbumsController albumsController = new AlbumsController();
            albumsController.getTracklistByAlbum(album, new ResultListener<List<Track>>() {
                @Override
                public void finish(List<Track> result) {
                    customTracklist = new ArrayList<>();
                    for (Track track : result) {
                        track.setCoverMedium(album.getCoverMedium());
                        customTracklist.add(track);
                    }
                    trackAdapter.setTrackList(customTracklist);
                    trackAdapter.notifyDataSetChanged();
                }
            });
            recyclerView.setAdapter(trackAdapter);
        }
        return view;
    }

    @Override
    public void getTrackFromAdapter(Track track) {
        fragmentAlbumDetailListener.getTrackFromFragment(track, customTracklist, fromSearch);
    }

    public interface FragmentAlbumDetailListener {
        void getTrackFromFragment(Track track, List<Track> customTracklist, boolean fromSearch);
    }

}
