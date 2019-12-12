package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.NetworkUtils;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.FavArtist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.FavTracks;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static android.graphics.Color.TRANSPARENT;

public class MediaPlayerFragment extends Fragment {

    public static final String KEY_TRACK = "keyTrack";
    public static final String KEY_TRACKLIST = "keyTracklist";
    private static final int ONE_SECOND = 1000;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler;
    private Runnable runnable;
    private TextView txtPlayerElapsed;
    private TextView txtPlayerDuration;
    private Track track;
    private List<Track> trackList;
    private boolean pause = false;

    /** ///////////       CODIGO DE FAVORITO     /////////////*/
    private static final String COLLECTION_FAV_TRACK = "FavTracks";
    private FloatingActionButton btnfav;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FirebaseFirestore firestore;
    private FavTracks favTracks = new FavTracks();



    public MediaPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        if (!NetworkUtils.isNetworkAvailable(getContext())) {
            view = inflater.inflate(R.layout.fragment_empty_state, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_media_player, container, false);

            btnfav = view.findViewById(R.id.fragment_media_player_button_fav);
            firestore = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();
            currentUser = auth.getCurrentUser();
            favTracks = new FavTracks();
            favTracks.setTracksList(new ArrayList<Track>());
            getCurrentFavTracksList();

            Bundle bundle = getArguments();
            track = (Track) bundle.getSerializable(KEY_TRACK);
            trackList = (List<Track>) bundle.getSerializable(KEY_TRACKLIST);

            handler = new Handler();
            ImageButton btnPlay = view.findViewById(R.id.btn_player_play);
            ImageButton btnNext = view.findViewById(R.id.btn_player_next);
            ImageButton btnPrevious = view.findViewById(R.id.btn_player_previous);
            ImageView imgPlayerAlbumCover = view.findViewById(R.id.player_album_cover);
            final TextView txtPlayerTrackName = view.findViewById(R.id.player_track_title);
            TextView txtPlayerTrackArtist = view.findViewById(R.id.player_track_artist);
            seekBar = view.findViewById(R.id.player_seekbar);
            txtPlayerElapsed = view.findViewById(R.id.txt_player_elapsed);
            txtPlayerDuration = view.findViewById(R.id.txt_player_duration);

            txtPlayerTrackName.setText(track.getTitle());
            txtPlayerTrackArtist.setText(track.getArtist().getName());
            Glide.with(view)
                .load(track.getCoverMedium())
                .placeholder(R.drawable.img_genre_placeholder)
                .into(imgPlayerAlbumCover);

            mediaPlayer = CustomMediaPlayer.getInstance();
            setAndPrepare(track.getPreview());
            getPlayerStats();

            btnPlay.setBackgroundColor(TRANSPARENT);
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    play();
                }
            });

            btnNext.setBackgroundColor(TRANSPARENT);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (track.getTrackPosition() < trackList.size()) {
                        goToTrack(track.getTrackPosition(), txtPlayerTrackName);
                    } else {
                        Toast.makeText(getContext(), getString(R.string.player_last_track), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnPrevious.setBackgroundColor(TRANSPARENT);
            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int previousTrackPosition = track.getTrackPosition() - 2;
                    if (track.getTrackPosition() >= 2) {
                        goToTrack(previousTrackPosition, txtPlayerTrackName);
                    } else {
                        Toast.makeText(getContext(), getString(R.string.player_first_track), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (mediaPlayer != null && fromUser) {
                        mediaPlayer.seekTo(progress * ONE_SECOND);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


            btnfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser == null) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    } else {
                        btnfav.setImageResource(R.drawable.ic_fav_active_64dp);
                        Toast.makeText(getContext(), "AGREGASTE UN FAVORITO", Toast.LENGTH_SHORT).show();
                        addTrackToFavList(track);
                    }
                }
            });


            play();
        }
        return view;
    }

    private void goToTrack(int previousTrackPosition, TextView txtPlayerTrackName) {
        stopPlaying();
        track = trackList.get(previousTrackPosition);
        mediaPlayer.reset();
        setAndPrepare(track.getPreview());
        play();
        getPlayerStats();
        initSeekbar();
        txtPlayerTrackName.setText(track.getTitle());
    }

    private void stopPlaying() {
        if (mediaPlayer.isPlaying() || pause) {
            mediaPlayer.stop();
        }
    }

    private void setAndPrepare(String preview) {
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
        getPlayerStats();
        initSeekbar();
    }

    private void initSeekbar() {
        seekBar.setMax(mediaPlayer.getDuration() / ONE_SECOND);

        runnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition() / ONE_SECOND;
                    seekBar.setProgress(currentPosition);
                    getPlayerStats();
                }
                handler.postDelayed(runnable, ONE_SECOND);
            }
        };
        handler.postDelayed(runnable, ONE_SECOND);
    }

    private void getPlayerStats() {
        int duration = mediaPlayer.getDuration() / ONE_SECOND;
        int remainingTime = (mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()) / ONE_SECOND;
        String elapsedTime = secondsToString(duration - remainingTime);

        txtPlayerElapsed.setText(elapsedTime);
        txtPlayerDuration.setText(secondsToString(duration));
    }

    private String secondsToString(int time) {
        return String.format(Locale.getDefault(), "%02d:%02d", time / 60, time % 60);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            if (handler != null) {
                handler.removeCallbacks(runnable);
            }
        }
    }


    private void addTrackToFavList(Track selectedTrack) {
        if (!favTracks.getTracksList().contains(selectedTrack)){
            favTracks.getTracksList().add(selectedTrack);
            firestore.collection(COLLECTION_FAV_TRACK)
                    .document(currentUser.getUid())
                    .set(favTracks);
        }
    }


    private void getCurrentFavTracksList(){
        if(currentUser == null){
            return;
        }
        firestore.collection(COLLECTION_FAV_TRACK)
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        favTracks = documentSnapshot.toObject(FavTracks.class);
                        if (favTracks==null){
                            favTracks = new FavTracks();
                            favTracks.setTracksList(new ArrayList<Track>());
                        }

                        if(favTracks.getTracksList().contains(track)){
                            btnfav.setImageResource(R.drawable.ic_fav_active_64dp);
                        }
                    }
                });
    }
}