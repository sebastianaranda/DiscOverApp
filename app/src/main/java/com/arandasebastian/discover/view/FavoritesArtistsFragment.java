package com.arandasebastian.discover.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.arandasebastian.discover.R;
import com.arandasebastian.discover.model.Artist;
import com.arandasebastian.discover.model.FavArtist;
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
    private ArtistAdapter artistAdapter;
    private TextView txtEmpty;

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
        View view = inflater.inflate(R.layout.fragment_favorites_artists, container, false);

        txtEmpty = view.findViewById(R.id.fragment_favorites_empty);
        txtEmpty.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_favorites_artists_recycler);
        artistAdapter = new ArtistAdapter(this);

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
                        if (!documentSnapshot.exists()){
                            favArtist = new FavArtist();
                            txtEmpty.setVisibility(View.VISIBLE);
                            artistAdapter.notifyDataSetChanged();
                            return;
                        }
                        if (favArtist.getArtistList().isEmpty()){
                            favArtist = new FavArtist();
                            txtEmpty.setVisibility(View.VISIBLE);
                            artistAdapter.notifyDataSetChanged();
                            return;
                        }
                        artistAdapter.setArtists(favArtist.getArtistList());
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