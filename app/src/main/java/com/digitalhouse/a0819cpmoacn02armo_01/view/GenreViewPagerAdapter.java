package com.digitalhouse.a0819cpmoacn02armo_01.view;

import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class GenreViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<GenreViewPagerFragment> genreViewPagerFragments;

    GenreViewPagerAdapter(@NonNull FragmentManager fm, List<Genre> genreList) {
        super(fm);
        genreViewPagerFragments = new ArrayList<>();
        for (Genre genre : genreList) {
            genreViewPagerFragments.add(GenreViewPagerFragment.getFragmentForPager(genre));
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return genreViewPagerFragments.get(position);
    }

    @Override
    public int getCount() {
        return genreViewPagerFragments.size();
    }

}
