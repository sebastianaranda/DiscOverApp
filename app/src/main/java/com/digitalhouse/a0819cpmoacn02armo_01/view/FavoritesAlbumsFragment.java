package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Album;
import com.digitalhouse.a0819cpmoacn02armo_01.model.ContainerAlbums;
import com.digitalhouse.a0819cpmoacn02armo_01.model.FavAlbum;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class FavoritesAlbumsFragment extends Fragment implements AlbumAdapter.AlbumAdapterListener {

    private FragmentFavoritesAlbumsListener fragmentFavoritesAlbumsListener;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private static final String COLLECTION_FAV_ALBUM = "FavAlbums";
    private FirebaseFirestore firestore;
    private FavAlbum favAlbum = new FavAlbum();
    private ContainerAlbums containerAlbums;
    private AlbumAdapter albumAdapter;
    private TextView txtEmpty;

    public FavoritesAlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentFavoritesAlbumsListener = (FragmentFavoritesAlbumsListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites_albums, container, false);

        txtEmpty = view.findViewById(R.id.fragment_favorites_albums_empty);
        txtEmpty.setVisibility(View.GONE);

        final RecyclerView recyclerView = view.findViewById(R.id.fragment_favorites_albums_recycler);
        albumAdapter = new AlbumAdapter(this);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        favAlbum = new FavAlbum();
        favAlbum.setAlbumsList(new ArrayList<Album>());

        firestore.collection(COLLECTION_FAV_ALBUM)
                .document(currentUser.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        favAlbum = documentSnapshot.toObject(FavAlbum.class);
                        if (!documentSnapshot.exists()){
                            favAlbum = new FavAlbum();
                            txtEmpty.setVisibility(View.VISIBLE);
                            albumAdapter.notifyDataSetChanged();
                            return;
                        }
                        if (favAlbum.getAlbumsList().isEmpty()){
                            favAlbum = new FavAlbum();
                            txtEmpty.setVisibility(View.VISIBLE);
                            albumAdapter.notifyDataSetChanged();
                            return;
                        }
                        albumAdapter.setAlbums(favAlbum.getAlbumsList());
                        albumAdapter.notifyDataSetChanged();
                    }
                });
        recyclerView.setAdapter(albumAdapter);
        return view;
    }

    @Override
    public void getAlbumFromAdapter(Album album) {
        fragmentFavoritesAlbumsListener.getFavoriteAlbumFromFragment(album);
    }

    public interface FragmentFavoritesAlbumsListener {
        void getFavoriteAlbumFromFragment(Album album);
    }

}