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
import com.digitalhouse.a0819cpmoacn02armo_01.model.Artist;

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
        TextView txtArtistName = fragmentView.findViewById(R.id.txt_artist_name);
        TextView txtYearsActive = fragmentView.findViewById(R.id.txt_artist_years_active);
        TextView txtArtistFans = fragmentView.findViewById(R.id.txt_artist_fans);

        Bundle bundle = getArguments();
        Artist selectedArtist = (Artist) bundle.getSerializable(KEY_ARTIST);

        Glide.with(fragmentView)
                .load(selectedArtist.getPictureBig())
                .placeholder(R.drawable.img_artist_placeholder)
                .into(imgArtistPicture);
        txtArtistName.setText(selectedArtist.getName());
        txtYearsActive.setText(selectedArtist.getYearsActive());
        txtArtistFans.setText(selectedArtist.getDescription());
        return fragmentView;
    }

}
