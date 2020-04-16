package com.arandasebastian.discover.view;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.arandasebastian.discover.R;
import com.arandasebastian.discover.model.Album;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        private ProgressBar progressBar;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbumCover = itemView.findViewById(R.id.img_album_cover);
            txtAlbumTitle = itemView.findViewById(R.id.txt_album_title);
            txtAlbumTracks = itemView.findViewById(R.id.txt_album_track_count);
            progressBar = itemView.findViewById(R.id.progress_bar_celdaAlbum);
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
                .placeholder(R.drawable.img_artist_placeholder).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            })
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
