package com.arandasebastian.discoverapp.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arandasebastian.discoverapp.R;
import com.arandasebastian.discoverapp.ResultListener;
import com.arandasebastian.discoverapp.controller.ArtistsController;
import com.arandasebastian.discoverapp.controller.NetworkUtils;
import com.arandasebastian.discoverapp.model.Artist;
import java.util.List;

public class ArtistsRecyclerFragment extends Fragment implements ArtistAdapter.ArtistAdapterListener {

    private FragmentArtistsListener fragmentArtistsListener;

    public ArtistsRecyclerFragment() {
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
        View view;
        if (!NetworkUtils.isNetworkAvailable(getContext())) {
            view = inflater.inflate(R.layout.fragment_empty_state, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_recycler_artists, container, false);
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
        }
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
