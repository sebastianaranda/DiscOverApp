package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistProfileFragment extends Fragment {

    public static final String KEY_ARTIST = "keyArtist";

    private ImageView fragmentArtistProfile_ImageView_ArtistImage;
    private TextView fragmentArtistProfile_TextView_artistName;
    private TextView fragmentArtistProfile_TextView_yearsActive;
    private TextView fragmentArtistProfile_TextView_ArtistDescription;


    public ArtistProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_artist_profile, container, false);

        fragmentArtistProfile_ImageView_ArtistImage = fragmentView.findViewById(R.id.fragmentArtistProfile_ImageView_ArtistImage);
        fragmentArtistProfile_TextView_artistName = fragmentView.findViewById(R.id.fragmentArtistProfile_TextView_artistName);
        fragmentArtistProfile_TextView_yearsActive = fragmentView.findViewById(R.id.fragmentArtistProfile_TextView_yearsActive);
        fragmentArtistProfile_TextView_ArtistDescription = fragmentView.findViewById(R.id.fragmentArtistProfile_TextView_ArtistDescription);

        Bundle bundle = getArguments();
        Artist selectedArtist = (Artist) bundle.getSerializable(KEY_ARTIST);

        fragmentArtistProfile_ImageView_ArtistImage.setImageResource(selectedArtist.getPictureSmall());
        fragmentArtistProfile_TextView_artistName.setText(selectedArtist.getName());
        fragmentArtistProfile_TextView_yearsActive.setText(selectedArtist.getYearsActive());
        fragmentArtistProfile_TextView_ArtistDescription.setText(selectedArtist.getDescription());
        return fragmentView;
    }

}
