package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artista;

public class MainActivity extends AppCompatActivity implements FragmentArtistas.FragmentArtistasListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pegarFragment(new FragmentArtistas(), R.id.mainActivity_Container);
    }

    private void pegarFragment(Fragment fragment, int layoutId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(layoutId, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void recibirArtista(Artista artista) {
        //TODO: (Seba) Agregar comportamiento para abrir detalle
    }

}
