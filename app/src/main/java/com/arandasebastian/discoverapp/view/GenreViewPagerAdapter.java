package com.arandasebastian.discoverapp.view;

import com.arandasebastian.discoverapp.model.Genre;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class GenreViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<GenreViewPagerFragment> genreViewPagerFragments;
    private List<Genre> genreList;

    GenreViewPagerAdapter(@NonNull FragmentManager fm, List<Genre> genreList) {
        super(fm);
        this.genreList = genreList;
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

    protected Genre getGenreFromPosition(int position) {
       return genreList.get(position);
    }

}
