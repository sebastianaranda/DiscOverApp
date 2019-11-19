package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.media.MediaPlayer;

public class CustomMediaPlayer {

    private static MediaPlayer mediaPlayer;

    private CustomMediaPlayer() {
        mediaPlayer = new MediaPlayer();
    }

    public static MediaPlayer getInstance() {
        if (mediaPlayer == null) {
            new CustomMediaPlayer();
        }
        return mediaPlayer;
    }

}
