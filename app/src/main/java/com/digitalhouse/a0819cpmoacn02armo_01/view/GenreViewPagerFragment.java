package com.digitalhouse.a0819cpmoacn02armo_01.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

import androidx.fragment.app.Fragment;

public class GenreViewPagerFragment extends Fragment {

    public static final String KEY_GENRE = "keyGenre";

    public GenreViewPagerFragment() {
        // Required empty public constructor
    }

    public static GenreViewPagerFragment getFragmentForPager(Genre genre){
        GenreViewPagerFragment genreViewPagerFragment = new GenreViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_GENRE, genre);
        genreViewPagerFragment.setArguments(bundle);
        return genreViewPagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre_view_pager, container, false);

        TextView txtGenrePagerName = view.findViewById(R.id.txt_viewpager_genre_name);
        ImageView imgGenrePagerImage = view.findViewById(R.id.img_viewpager_genre);

        Bundle bundle = getArguments();
        Genre genre = (Genre) bundle.getSerializable(KEY_GENRE);

        txtGenrePagerName.setText(genre.getName());
        Glide.with(getContext())
                .load(genre.getPicture())
                .placeholder(R.drawable.ic_play)
                .error(R.drawable.img_genre_placeholder)
                .into(imgGenrePagerImage)
        ;
        return view;
    }

}
