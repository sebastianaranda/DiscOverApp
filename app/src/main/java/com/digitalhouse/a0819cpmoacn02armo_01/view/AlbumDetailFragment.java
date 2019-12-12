package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.AlbumsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.FavAlbum;
import com.digitalhouse.a0819cpmoacn02armo_01.model.FavArtist;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AlbumDetailFragment extends Fragment {

    public static final String DETAIL_ALBUM_KEY = "detailAlbumKey";
    private FloatingActionButton btnfav;
    private TextView txtAlbumTitle;
    private TextView txtAlbumArtist;
    private TextView txtReleaseDate;
    private TextView txtAlbumDuration;
    private Album selectedAlbum;
    private ImageView imgDetailAlbumCover;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    /** ///////////       CODIGO DE FAVORITO     /////////////*/

    private static final String COLLECTION_FAV_ALBUM = "FavAlbums";
    private FirebaseFirestore firestore;
    private FavAlbum favAlbum = new FavAlbum();

    public AlbumDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_album_detail, container, false);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        favAlbum = new FavAlbum();
        favAlbum.setAlbumsList(new ArrayList<Album>());
        getCurrentFavAlbumsList();

        txtAlbumTitle = view.findViewById(R.id.txt_detail_abum_title);
        txtAlbumArtist = view.findViewById(R.id.txt_detail_by_artist);
        txtReleaseDate = view.findViewById(R.id.txt_release_date);
        txtAlbumDuration = view.findViewById(R.id.txt_album_duration);
        imgDetailAlbumCover = view.findViewById(R.id.img_detail_album_cover);
        btnfav = view.findViewById(R.id.fragment_album_detail_button_fav);

        Bundle bundle = getArguments();
        selectedAlbum = (Album) bundle.getSerializable(DETAIL_ALBUM_KEY);

        AlbumsController albumsController = new AlbumsController();
        albumsController.getAlbumById(selectedAlbum.getId(), new ResultListener<Album>() {
            @Override
            public void finish(Album result) {
                //TODO: sacar esto
                selectedAlbum = result;
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

        //TODO: agregar de favoritos
        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser == null){
                    startActivity(new Intent(getContext(),LoginActivity.class));
                } else {
                    btnfav.setImageResource(R.drawable.ic_fav_active_64dp);
                    Toast.makeText(getContext(), "AGREGASTE UN FAVORITO", Toast.LENGTH_SHORT).show();
                    addAlbumToFavList(selectedAlbum);
                }
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

    private void addAlbumToFavList(Album selectedAlbum) {
        if (!favAlbum.getAlbumsList().contains(selectedAlbum)){
            favAlbum.getAlbumsList().add(selectedAlbum);
            firestore.collection(COLLECTION_FAV_ALBUM)
                    .document(currentUser.getUid())
                    .set(favAlbum);
        }
    }


    private void getCurrentFavAlbumsList(){
        if(currentUser == null){
            return;
        }
        firestore.collection(COLLECTION_FAV_ALBUM)
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        favAlbum = documentSnapshot.toObject(FavAlbum.class);
                        if (favAlbum==null){
                            favAlbum = new FavAlbum();
                            favAlbum.setAlbumsList(new ArrayList<Album>());
                        }
                        if(favAlbum.getAlbumsList().contains(selectedAlbum)){
                            btnfav.setImageResource(R.drawable.ic_fav_active_64dp);
                        }
                    }
                });
    }

}
