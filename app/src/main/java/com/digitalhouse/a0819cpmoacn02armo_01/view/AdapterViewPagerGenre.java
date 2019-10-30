package com.digitalhouse.a0819cpmoacn02armo_01.view;

import com.digitalhouse.a0819cpmoacn02armo_01.model.Genre;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterViewPagerGenre extends FragmentStatePagerAdapter {

    private List<GenreViewPagerFragment> genreViewPagerFragments;

    public AdapterViewPagerGenre(@NonNull FragmentManager fm, List<Fragment> genres) {
        super(fm);
        genreViewPagerFragments = new ArrayList<>();
        /*for (Fragment genre : genres) {
            GenreViewPagerFragment fragment = GenreViewPagerFragment.getFragmentForPager(genre);
            genreViewPagerFragments.add(fragment);
        }*/
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

    public void setGenreViewPagerFragments(List<GenreViewPagerFragment> genreViewPagerFragments) {
        this.genreViewPagerFragments = genreViewPagerFragments;
    }

}
