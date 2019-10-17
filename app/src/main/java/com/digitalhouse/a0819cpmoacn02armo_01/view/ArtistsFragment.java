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
import com.digitalhouse.a0819cpmoacn02armo_01.controller.ArtistsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import java.util.List;

public class ArtistsFragment extends Fragment implements ArtistAdapter.ArtistAdapterListener {

    private FragmentArtistsListener fragmentArtistsListener;

    public ArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentArtistsListener = (FragmentArtistsListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_artists_recycler);
        final ArtistAdapter artistAdapter = new ArtistAdapter(this);
        ArtistsController artistsController = new ArtistsController();
        artistsController.getArtistsFromDao(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> result) {
                artistAdapter.setArtists(result);
                artistAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(artistAdapter);
        return view;
    }

    @Override
    public void getArtistFromAdapter(Artist artist) {
        fragmentArtistsListener.getArtistFromFragment(artist);
    }

    public interface FragmentArtistsListener {
        void getArtistFromFragment(Artist artist);
    }

}
