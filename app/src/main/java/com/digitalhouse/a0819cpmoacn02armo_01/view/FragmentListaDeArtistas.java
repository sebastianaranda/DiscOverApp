package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.ArtistasController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artista;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaDeArtistas extends Fragment implements ArtistaAdapter.ArtistaAdapterListener {

    private RecyclerView recyclerView;
    private FragmentArtistasListener fragmentArtistasListener;


    public FragmentListaDeArtistas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_de_artistas, container, false);

        recyclerView = view.findViewById(R.id.fragmentArtistasRecyclerView);

        final ArtistaAdapter artistaAdapter = new ArtistaAdapter(this);
        ArtistasController artistasController = new ArtistasController();

        artistasController.traerArtistas(new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> result) {
                artistaAdapter.setListaDeArtistas(result);
                artistaAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(artistaAdapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentArtistasListener = (FragmentArtistasListener) context;
    }

    @Override
    public void informarArtista(Artista artista) {
        fragmentArtistasListener.recibirArtista(artista);
    }

    public interface FragmentArtistasListener {
        void recibirArtista(Artista artista);
    }

}
