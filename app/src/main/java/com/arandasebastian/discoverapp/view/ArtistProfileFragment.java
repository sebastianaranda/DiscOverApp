package com.arandasebastian.discoverapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.arandasebastian.discoverapp.R;
import com.arandasebastian.discoverapp.ResultListener;
import com.arandasebastian.discoverapp.controller.AlbumsController;
import com.arandasebastian.discoverapp.controller.ArtistsController;
import com.arandasebastian.discoverapp.controller.NetworkUtils;
import com.arandasebastian.discoverapp.model.Album;
import com.arandasebastian.discoverapp.model.Artist;
import com.arandasebastian.discoverapp.model.FavArtist;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class ArtistProfileFragment extends Fragment implements AlbumAdapter.AlbumAdapterListener {

    public static final String KEY_ARTIST = "keyArtist";
    private AlbumsRecyclerFragment.FragmentAlbumsListener fragmentAlbumsListener;
    ProgressBar progressBar;
    private FloatingActionButton btnFav;
    private Artist selectedArtist;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private TextView txtArtistFans;
    private ImageView imgArtistPicture;
    private CollapsingToolbarLayout collapsingToolbarLayoutTitle;

    private static final String COLLECTION_FAV_ARTIST = "FavArtists";
    private FirebaseFirestore firestore;
    private FavArtist favArtist = new FavArtist();

    public ArtistProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentAlbumsListener = (AlbumsRecyclerFragment.FragmentAlbumsListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView;
        if (!NetworkUtils.isNetworkAvailable(getContext())) {
            fragmentView = inflater.inflate(R.layout.fragment_empty_state, container, false);
        } else {
            fragmentView = inflater.inflate(R.layout.fragment_artist_profile, container, false);

            firestore = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();
            currentUser = auth.getCurrentUser();

            favArtist = new FavArtist();
            favArtist.setArtistList(new ArrayList<Artist>());

            imgArtistPicture = fragmentView.findViewById(R.id.img_artist_picture);

            txtArtistFans = fragmentView.findViewById(R.id.txt_artist_fans);
            btnFav = fragmentView.findViewById(R.id.fragment_artist_profile_button_fav);
            emptyFavIcon();
            btnFav.setClickable(false);

            collapsingToolbarLayoutTitle = fragmentView.findViewById(R.id.artist_profile_collapsing_toolbar_layout);

            progressBar = fragmentView.findViewById(R.id.progress_bar_profile);

            Bundle bundle = getArguments();
            selectedArtist = (Artist) bundle.getSerializable(KEY_ARTIST);

            ArtistsController artistsController = new ArtistsController();
            artistsController.getArtistByID(new ResultListener<Artist>() {
                @Override
                public void finish(Artist result) {
                    txtArtistFans.setText(String.valueOf(result.getNbFans()));
                    Glide.with(fragmentView)
                            .load(result.getPictureBig())
                            .placeholder(R.drawable.img_artist_placeholder)
                            .into(imgArtistPicture);
                    collapsingToolbarLayoutTitle.setTitle(result.getName());
                    selectedArtist = result;
                }
            }, selectedArtist.getId());

            collapsingToolbarLayoutTitle.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
            collapsingToolbarLayoutTitle.setCollapsedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
            RecyclerView recyclerView = fragmentView.findViewById(R.id.fragment_albums_recycler);
            final AlbumAdapter albumAdapter = new AlbumAdapter(this);
            recyclerView.setAdapter(albumAdapter);
            progressBar.setVisibility(View.VISIBLE);
            AlbumsController albumsController = new AlbumsController();
            albumsController.getAlbumsByArtist(selectedArtist, new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> result) {
                    albumAdapter.setAlbums(result);
                    albumAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            });

            getCurrentFavArtistsList();

            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser == null) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    } else {
                        addArtistToFavList(selectedArtist);
                    }
                }
            });
        }
        return fragmentView;
    }

    @Override
    public void getAlbumFromAdapter(Album album) {
        fragmentAlbumsListener.geAlbumFromFragment(album);
    }

    private void addArtistToFavList(Artist selectedArtist) {
        if (!favArtist.getArtistList().contains(selectedArtist)) {
            favArtist.getArtistList().add(selectedArtist);
            Toast.makeText(getContext(), selectedArtist.getName() + getString(R.string.txt_favorite_artist_added), Toast.LENGTH_SHORT).show();
            fillFavIcon();
        } else {
            favArtist.getArtistList().remove(selectedArtist);
            Toast.makeText(getContext(), selectedArtist.getName() + getString(R.string.txt_favorite_artist_removed), Toast.LENGTH_SHORT).show();
            emptyFavIcon();
        }
        firestore.collection(COLLECTION_FAV_ARTIST)
                .document(currentUser.getUid())
                .set(favArtist);
    }

    private void getCurrentFavArtistsList(){
        if(currentUser == null){
            return;
        }
        firestore.collection(COLLECTION_FAV_ARTIST)
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        favArtist = documentSnapshot.toObject(FavArtist.class);
                        if (favArtist==null){
                            favArtist = new FavArtist();
                            favArtist.setArtistList(new ArrayList<Artist>());
                        }

                        if(favArtist.getArtistList().contains(selectedArtist)){
                            fillFavIcon();
                        }
                    }
                });
        enableOnClickFav();
    }

    private void enableOnClickFav(){
        btnFav.setClickable(true);
    }

    private void fillFavIcon(){
        btnFav.setImageResource(R.drawable.ic_fav_active_64dp);
    }

    private void emptyFavIcon(){
        btnFav.setImageResource(R.drawable.ic_fav_border_64dp);
    }

}