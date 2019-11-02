package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private List<Artist> artists;
    private ArtistAdapterListener artistAdapterListener;

    public ArtistAdapter(ArtistAdapterListener artistAdapterListener) {
        this.artists = new ArrayList<>();
        this.artistAdapterListener = artistAdapterListener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        holder.bindArtist(artists.get(position));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        private TextView txtArtistName;
        private TextView txtAlbumCount;
        private ImageView imgArtistImage;

        public ArtistViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtArtistName = itemView.findViewById(R.id.txt_artist_name);
            txtAlbumCount = itemView.findViewById(R.id.txt_album_count);
            imgArtistImage = itemView.findViewById(R.id.img_artist_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    artistAdapterListener.getArtistFromAdapter(artists.get(getAdapterPosition()));
                }
            });
        }

        private void bindArtist(Artist artist) {
            txtArtistName.setText(artist.getName());
            txtAlbumCount.setText(String.valueOf(artist.getNbAlbum()));
            Glide.with(itemView)
                    .load(artist.getPictureSmall())
                    .placeholder(R.drawable.img_artist_placeholder)
                    .into(imgArtistImage);
        }
    }

    public interface ArtistAdapterListener {
        void getArtistFromAdapter(Artist artist);
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

}
