package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.ArtistsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements TrackAdapter.TrackAdapterListener {

    private FragmentSearchListener fragmentSearchListener;
    private List<Track> customTracklist;
    private EditText edtSearch;
    private boolean fromSearch = true;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentSearchListener = (FragmentSearchListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        edtSearch = view.findViewById(R.id.edt_search);
        Button button = view.findViewById(R.id.btn_search);
        RecyclerView recyclerView = view.findViewById(R.id.search_artist_recycler);

        final ArtistsController artistsController = new ArtistsController();
        final TrackAdapter trackAdapter = new TrackAdapter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch(artistsController, trackAdapter);

            }
        });

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(artistsController, trackAdapter);
                    return true;
                }
                return false;
            }
        });

        recyclerView.setAdapter(trackAdapter);
        return view;
    }

    private void performSearch(ArtistsController artistsController, final TrackAdapter trackAdapter) {
        artistsController.getArtistTracksByName(new ResultListener<List<Track>>() {
            @Override
            public void finish(List<Track> result) {
                customTracklist = new ArrayList<>();
                for (Track track : result) {
                    track.setCoverMedium(track.getAlbum().getCoverMedium());
                    customTracklist.add(track);
                }
                trackAdapter.setTrackList(result);
                trackAdapter.notifyDataSetChanged();
            }
        }, edtSearch.getText().toString());
    }

    @Override
    public void getTrackFromAdapter(Track track) {
        fragmentSearchListener.getTrackFromSearchFragment(track, customTracklist, fromSearch);
    }

    public interface FragmentSearchListener {
        void getTrackFromSearchFragment(Track track, List<Track> customTracklist, boolean fromSearch);
    }

}