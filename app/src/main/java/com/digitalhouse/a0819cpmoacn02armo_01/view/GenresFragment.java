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
import com.digitalhouse.a0819cpmoacn02armo_01.controller.GenresController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

import java.util.List;

public class GenresFragment extends Fragment implements GenresAdapter.GenreAdapterListener {

    private GenresFragmentListener genresFragmentListener;

    public GenresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        genresFragmentListener = (GenresFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genres, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.genres_fragment_recycler);
        final GenresAdapter genresAdapter = new GenresAdapter(this);
        GenresController genresController = new GenresController();
        genresController.getGenresFromDao(new ResultListener<List<Genre>>() {
            @Override
            public void finish(List<Genre> result) {
                genresAdapter.setGenreList(result);
                genresAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(genresAdapter);
        return view;
    }

    @Override
    public void getGenreFromAdapter(Genre genre) {
        genresFragmentListener.getGenreFromFragment(genre);
    }

    public interface GenresFragmentListener {
        void getGenreFromFragment(Genre genre);
    }

}
