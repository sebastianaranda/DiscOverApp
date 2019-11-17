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

public class AlbumDetailFragment extends Fragment {

    public static final String DETAIL_ALBUM_KEY = "detailAlbumKey";
    private ImageView imgDetailAlbumCover;
    private TextView txtAlbumArtist;
    private TextView txtReleaseDate;
    private TextView txtAlbumDuration;


    public AlbumDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_album_detail, container, false);

        imgDetailAlbumCover = view.findViewById(R.id.img_detail_album_cover);
        txtAlbumArtist = view.findViewById(R.id.txt_detail_by_artist);
        txtReleaseDate = view.findViewById(R.id.txt_release_date);
        txtAlbumDuration = view.findViewById(R.id.txt_album_duration);

        Bundle bundle = getArguments();
        final Album album = (Album) bundle.getSerializable(DETAIL_ALBUM_KEY);

        AlbumsController albumsController = new AlbumsController();
        albumsController.getAlbumById(album.getId(), new ResultListener<Album>() {
            @Override
            public void finish(Album result) {
                //TODO: (Juan) Resolver este pedido
                txtAlbumArtist.setText(result.getArtist().getId());
                txtReleaseDate.setText(result.getReleaseDate());
                txtAlbumDuration.setText(result.getDurationMinutes());
                Glide.with(view)
                    .load(result.getCoverMedium())
                    .placeholder(R.drawable.img_genre_placeholder)
                    .into(imgDetailAlbumCover);
            }
        });

        return view;
    }

}
