package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.AlbumsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AlbumDetailFragment extends Fragment {

    public static final String DETAIL_ALBUM_KEY = "detailAlbumKey";
    private TextView txtAlbumTitle;
    private TextView txtAlbumArtist;
    private TextView txtReleaseDate;
    private TextView txtAlbumDuration;
    private ImageView imgDetailAlbumCover;


    public AlbumDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_album_detail, container, false);

        txtAlbumTitle = view.findViewById(R.id.txt_detail_abum_title);
        txtAlbumArtist = view.findViewById(R.id.txt_detail_by_artist);
        txtReleaseDate = view.findViewById(R.id.txt_release_date);
        txtAlbumDuration = view.findViewById(R.id.txt_album_duration);
        imgDetailAlbumCover = view.findViewById(R.id.img_detail_album_cover);

        Bundle bundle = getArguments();
        final Album album = (Album) bundle.getSerializable(DETAIL_ALBUM_KEY);

        AlbumsController albumsController = new AlbumsController();
        albumsController.getAlbumById(album.getId(), new ResultListener<Album>() {
            @Override
            public void finish(Album result) {
                txtAlbumTitle.setText(result.getTitle());
                txtAlbumArtist.setText(result.getArtist().getName());
                txtReleaseDate.setText(getReleaseYear(result.getReleaseDate()));
                txtAlbumDuration.setText(getDurationMinutes(result.getDuration()));
                Glide.with(view)
                    .load(result.getCoverMedium())
                    .placeholder(R.drawable.img_genre_placeholder)
                    .into(imgDetailAlbumCover);
            }
        });

        return view;
    }

    public String getDurationMinutes(int time) {
        return String.format(Locale.getDefault(), "%02d:%02d:%02d",time / 3600, (time % 3600) / 60, time % 60);
    }

    private String getReleaseYear(Date releaseDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

}
