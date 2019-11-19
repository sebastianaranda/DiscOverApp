package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;

import java.io.IOException;
import java.net.URL;

import static android.graphics.Color.TRANSPARENT;

public class MediaPlayerFragment extends Fragment {

    public static final String KEY_TRACK = "keyTrack";
    private MediaPlayer mediaPlayer;
    private boolean pause = false;


    public MediaPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_player, container, false);
        Bundle bundle = getArguments();
        Track track = (Track) bundle.getSerializable(KEY_TRACK);

        ImageButton btnPlay = view.findViewById(R.id.btn_player_play);
        ImageView imgPlayerAlbumCover = view.findViewById(R.id.player_album_cover);
        TextView txtPlayerTrackName = view.findViewById(R.id.player_track_title);
        TextView txtPlayerTrackArtist = view.findViewById(R.id.player_track_artist);
        txtPlayerTrackName.setText(track.getTitle());
        txtPlayerTrackArtist.setText(track.getArtist().getName());
        Glide.with(view)
            .load(track.getCoverMedium())
            .placeholder(R.drawable.img_genre_placeholder)
            .into(imgPlayerAlbumCover);

        mediaPlayer = CustomMediaPlayer.getInstance();
        setAndPrepare(track.getPreview());

        btnPlay.setBackgroundColor(TRANSPARENT);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        return view;
    }

    private void setAndPrepare(URL preview) {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(String.valueOf(preview));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void play() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            pause = true;
        } else {
            if (pause) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                mediaPlayer.start();
                pause = false;
            } else {
                mediaPlayer.start();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.reset();
    }

}
