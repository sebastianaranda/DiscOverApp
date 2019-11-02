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
import com.digitalhouse.a0819cpmoacn02armo_01.controller.GenresController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;
import java.util.List;

public class GenresRecyclerFragment extends Fragment implements GenresRecyclerAdapter.GenreAdapterListener {

    private GenresFragmentListener genresFragmentListener;

    public GenresRecyclerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_recycler_genres, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.genres_fragment_recycler);
        final GenresRecyclerAdapter genresRecyclerAdapter = new GenresRecyclerAdapter(this);
        GenresController genresController = new GenresController();
        genresController.getGenresFromDao(new ResultListener<List<Genre>>() {
            @Override
            public void finish(List<Genre> result) {
                genresRecyclerAdapter.setGenreList(result);
                genresRecyclerAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(genresRecyclerAdapter);
        return view;
    }

    @Override
    public void getGenreFromAdapter(List<Genre> genre, Integer pos) {
        genresFragmentListener.getGenreFromFragment(genre, pos);
    }

    public interface GenresFragmentListener {
        void getGenreFromFragment(List<Genre> genre, Integer pos);
    }

}
