package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artista;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ArtistaViewHolder> {

    private List<Artista> artistas;
    private ArtistaAdapterListener artistaListener;

    public ArtistaAdapter(ArtistaAdapterListener artistaListener) {
        this.artistas = new ArrayList<>();
        this.artistaListener = artistaListener;
    }

    public ArtistaAdapter(List<Artista> artistas) {
        this.artistas = artistas;
    }

    @NonNull
    @Override
    public ArtistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_artista, parent, false);
        return new ArtistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistaViewHolder holder, int position) {
        Artista artista = artistas.get(position);
        holder.bindArtista(artista);
    }

    @Override
    public int getItemCount() {
        return artistas.size();
    }

    public class ArtistaViewHolder extends RecyclerView.ViewHolder {

        private TextView txtArtistaNombre;
        private TextView txtArtistaCantidadAlbumes;
        private ImageView imgArtistaImagen;

        public ArtistaViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtArtistaNombre = itemView.findViewById(R.id.txtMainArtistaNombre);
            txtArtistaCantidadAlbumes = itemView.findViewById(R.id.txtMainAlbumesCantidad);
            imgArtistaImagen = itemView.findViewById(R.id.imgArtistaMain);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void bindArtista(Artista artista) {
            txtArtistaNombre.setText(artista.getName());
            txtArtistaCantidadAlbumes.setText(String.valueOf(artista.getNbAlbum()));
            //TODO: (Juan) Cargar imagen por URL
            //imgArtistaImagen.setImageDrawable(artista.getPictureSmall());
        }
    }

    public interface ArtistaAdapterListener {
        void informarArtista(Artista artista);
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

}
