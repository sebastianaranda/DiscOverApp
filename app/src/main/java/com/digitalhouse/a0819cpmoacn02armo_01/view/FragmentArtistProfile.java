package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArtistProfile extends Fragment {

    public static final String CLAVE_ARTISTA = "claveArtista";

    private ImageView fragmentPerfilArtista_ImageView_FotoArtista;
    private TextView fragmentPerfilArtista_TextView_NombreArtista;
    private TextView fragmentPerfilArtista_TextView_ActividadArtista;
    private TextView fragmentPerfilArtista_TextView_DescripcionArtista;


    public FragmentArtistProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment = inflater.inflate(R.layout.fragment_artist_profile, container, false);

        fragmentPerfilArtista_ImageView_FotoArtista = vistaDelFragment.findViewById(R.id.fragmentPerfilArtista_ImageView_FotoArtista);
        fragmentPerfilArtista_TextView_NombreArtista = vistaDelFragment.findViewById(R.id.fragmentPerfilArtista_TextView_NombreArtista);
        fragmentPerfilArtista_TextView_ActividadArtista = vistaDelFragment.findViewById(R.id.fragmentPerfilArtista_TextView_ActividadArtista);
        fragmentPerfilArtista_TextView_DescripcionArtista = vistaDelFragment.findViewById(R.id.fragmentPerfilArtista_TextView_DescripcionArtista);

        Bundle bundle = getArguments();

        Artist artistSeleccionado = (Artist) bundle.getSerializable(CLAVE_ARTISTA);

        fragmentPerfilArtista_ImageView_FotoArtista.setImageResource(artistSeleccionado.getPictureSmall());
        fragmentPerfilArtista_TextView_NombreArtista.setText(artistSeleccionado.getName());
        fragmentPerfilArtista_TextView_ActividadArtista.setText(artistSeleccionado.getActividad());
        fragmentPerfilArtista_TextView_DescripcionArtista.setText(artistSeleccionado.getDescripcion());


        return vistaDelFragment;
    }

}
