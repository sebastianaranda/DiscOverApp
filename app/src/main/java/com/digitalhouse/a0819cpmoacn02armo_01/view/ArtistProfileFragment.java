package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ArtistProfileFragment extends Fragment {

    public static final String KEY_ARTIST = "keyArtist";

    public ArtistProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_artist_profile, container, false);

        ImageView imgArtistPicture = fragmentView.findViewById(R.id.img_artist_picture);
        //TODO: chequear si podemos pedir este dato a la API y modificar este codigo
        TextView txtArtistFans = fragmentView.findViewById(R.id.txt_artist_fans);
        CollapsingToolbarLayout collapsingToolbarLayoutTitle = fragmentView.findViewById(R.id.artist_profile_collapsing_toolbar_layout);

        Bundle bundle = getArguments();
        Artist selectedArtist = (Artist) bundle.getSerializable(KEY_ARTIST);

        Glide.with(fragmentView)
                .load(selectedArtist.getPictureBig())
                .placeholder(R.drawable.img_artist_placeholder)
                .into(imgArtistPicture);

        collapsingToolbarLayoutTitle.setTitle(selectedArtist.getName());
        collapsingToolbarLayoutTitle.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        collapsingToolbarLayoutTitle.setCollapsedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        return fragmentView;
    }

}
