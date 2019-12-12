package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.digitalhouse.a0819cpmoacn02armo_01.model.ContainerArtists;
import com.digitalhouse.a0819cpmoacn02armo_01.model.FavArtist;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;

public class FavoritesArtistsFragment extends Fragment implements ArtistAdapter.ArtistAdapterListener {

    private FragmentFavoritesArtistsListener fragmentFavoritesArtistsListener;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private static final String COLLECTION_FAV_ARTIST = "FavArtists";
    private FirebaseFirestore firestore;
    private FavArtist favArtist = new FavArtist();
    private ContainerArtists containerArtists;

    public FavoritesArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentFavoritesArtistsListener = (FragmentFavoritesArtistsListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites_artists, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_favorites_artists_recycler);
        final ArtistAdapter artistAdapter = new ArtistAdapter(this);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        favArtist = new FavArtist();
        favArtist.setArtistList(new ArrayList<Artist>());

        firestore.collection(COLLECTION_FAV_ARTIST)
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        favArtist = documentSnapshot.toObject(FavArtist.class);
                        if (containerArtists == null){
                            containerArtists = new ContainerArtists();
                        }
                        containerArtists.setArtistsList(favArtist.getArtistList());
                        artistAdapter.setArtists(containerArtists.getArtistsList());
                        artistAdapter.notifyDataSetChanged();
                    }
                });

        recyclerView.setAdapter(artistAdapter);
        return view;
    }

    @Override
    public void getArtistFromAdapter(Artist artist) {
        fragmentFavoritesArtistsListener.getFavoriteArtistFromFragment(artist);
    }

    public interface FragmentFavoritesArtistsListener {
        void getFavoriteArtistFromFragment(Artist artist);
    }

}