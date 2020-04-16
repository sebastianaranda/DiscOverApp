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
import com.arandasebastian.discover.controller.GenresController;
import com.arandasebastian.discover.model.Genre;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GenresRecyclerFragment extends Fragment implements GenresRecyclerAdapter.GenreAdapterListener {

    private GenresFragmentListener genresFragmentListener;
    private List<Genre> genreList;

    public GenresRecyclerFragment() {
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
        genreList = new ArrayList<>();
        GenresController genresController = new GenresController();
        genresController.getGenresFromDao(new ResultListener<List<Genre>>() {
            @Override
            public void finish(List<Genre> result) {
                genreList = result;
                Genre newGenre = genreList.get(0);
                String urlString = "https://e-cdns-images.dzcdn.net/images/playlist/f1ac18441ab1dabc94282e4d1d5f4955/1000x1000.jpg";
                try {
                    URL newPicture = new URL(urlString);
                    newGenre.setPictureBig(newPicture);
                    newGenre.setPicture(newPicture);
                    genreList.get(0).setPicture(newPicture);
                    genreList.get(0).setPictureBig(newPicture);
                    genreList.get(0).setName("Top 100 world");
                } catch (Exception e){
                }
                genresRecyclerAdapter.setGenreList(genreList);
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
