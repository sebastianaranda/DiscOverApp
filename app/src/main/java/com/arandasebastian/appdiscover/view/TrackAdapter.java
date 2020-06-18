package com.arandasebastian.appdiscover.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.arandasebastian.appdiscover.R;
import com.arandasebastian.appdiscover.model.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {

    private List<Track> trackList;
    private TrackAdapterListener trackAdapterListener;

    public TrackAdapter(TrackAdapterListener trackAdapterListener) {
        this.trackList = new ArrayList<>();
        this.trackAdapterListener = trackAdapterListener;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrackViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.track_recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        holder.bindTrack(trackList.get(position));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTrackAlbumCover;
        private TextView txtTrackName;
        private TextView txtByArtist;


        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTrackAlbumCover = itemView.findViewById(R.id.img_track_album);
            txtTrackName = itemView.findViewById(R.id.txt_track_name);
            txtByArtist = itemView.findViewById(R.id.txt_by_artist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trackAdapterListener.getTrackFromAdapter(trackList.get(getAdapterPosition()));

                }
            });
        }

        private void bindTrack(Track track) {
            txtTrackName.setText(track.getTitle());
            txtByArtist.setText(track.getArtist().getName());
            Glide.with(itemView)
                    .load(track.getCoverMedium())
                    .placeholder(R.drawable.img_artist_placeholder)
                    .into(imgTrackAlbumCover);
        }

    }

    public interface TrackAdapterListener {
        void getTrackFromAdapter(Track track);
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

}
