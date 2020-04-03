package com.arandasebastian.discoverapp.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

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
import com.arandasebastian.discoverapp.R;
import com.arandasebastian.discoverapp.ResultListener;
import com.arandasebastian.discoverapp.controller.NetworkUtils;
import com.arandasebastian.discoverapp.controller.TrackController;
import com.arandasebastian.discoverapp.model.Track;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
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
    private FloatingActionButton btnShare;
    private ImageView imgGradientBg,imgBgColor;
    private Palette.Swatch swatch;

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

            btnShare = view.findViewById(R.id.fragment_media_player_share);

            Bundle bundle = getArguments();
            track = (Track) bundle.getSerializable(KEY_TRACK);
            trackList = (List<Track>) bundle.getSerializable(KEY_TRACKLIST);

            handler = new Handler();
            ImageButton btnPlay = view.findViewById(R.id.btn_player_play);
            ImageButton btnNext = view.findViewById(R.id.btn_player_next);
            ImageButton btnPrevious = view.findViewById(R.id.btn_player_previous);
            final ImageView imgPlayerAlbumCover = view.findViewById(R.id.player_album_cover);
            final TextView txtPlayerTrackName = view.findViewById(R.id.player_track_title);
            TextView txtPlayerTrackArtist = view.findViewById(R.id.player_track_artist);
            seekBar = view.findViewById(R.id.player_seekbar);
            txtPlayerElapsed = view.findViewById(R.id.txt_player_elapsed);
            txtPlayerDuration = view.findViewById(R.id.txt_player_duration);

            imgBgColor = view.findViewById(R.id.media_player_bg_color);
            imgGradientBg = view.findViewById(R.id.media_player_gradient_bg);

            txtPlayerTrackName.setText(track.getTitle());
            txtPlayerTrackArtist.setText(track.getArtist().getName());

            Glide.with(view)
                    .asBitmap()
                    .load(track.getCoverMedium())
                    .placeholder(R.drawable.img_genre_placeholder)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            imgPlayerAlbumCover.setImageBitmap(resource);
                            Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(@Nullable Palette palette) {
                                    swatch = palette.getDominantSwatch();
                                    if (swatch != null){
                                        imgBgColor.setBackgroundColor(swatch.getRgb());
                                        btnShare.setBackgroundTintList(ColorStateList.valueOf(swatch.getBodyTextColor()));
                                        seekBar.setProgressTintList(ColorStateList.valueOf(swatch.getRgb()));
                                        seekBar.setThumbTintList(ColorStateList.valueOf(swatch.getRgb()));
                                    }
                                }
                            });
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
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

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TrackController trackController = new TrackController();
                    trackController.getTrackByID(new ResultListener<Track>() {
                        @Override
                        public void finish(Track result) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.txt_media_player_share_text) + result.getArtist().getName() + getString(R.string.txt_media_player_text_space) + result.getShare());
                            intent.setType("text/plain");
                            Intent shareIntent = Intent.createChooser(intent,getString(R.string.txt_media_player_share_chooser));
                            startActivity(shareIntent);
                        }
                    },track.getId());
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

}