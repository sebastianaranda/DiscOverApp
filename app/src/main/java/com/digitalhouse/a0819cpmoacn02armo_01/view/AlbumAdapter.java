package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albums;
    private AlbumAdapterListener albumAdapterListener;

    public AlbumAdapter(AlbumAdapterListener albumAdapterListener) {
        this.albums = new ArrayList<>();
        this.albumAdapterListener = albumAdapterListener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bindAlbum(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAlbumCover;
        private TextView txtAlbumTitle;
        private TextView txtAlbumTracks;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbumCover = itemView.findViewById(R.id.img_album_cover);
            txtAlbumTitle = itemView.findViewById(R.id.txt_album_title);
            txtAlbumTracks = itemView.findViewById(R.id.txt_album_track_count);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    albumAdapterListener.getAlbumFromAdapter(albums.get(getAdapterPosition()));

                }
            });
        }

        private void bindAlbum(Album album) {
            txtAlbumTitle.setText(album.getTitle());
            txtAlbumTracks.setText(getReleaseYear(album.getReleaseDate()));
            Glide.with(itemView)
                .load(album.getCoverMedium())
                .placeholder(R.drawable.img_artist_placeholder)
                .into(imgAlbumCover);
        }

    }

    private String getReleaseYear(Date releaseDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public interface AlbumAdapterListener {
        void getAlbumFromAdapter(Album album);
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}
