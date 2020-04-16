package com.arandasebastian.discover.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arandasebastian.discover.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmptyStateFragment extends Fragment {


    public EmptyStateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty_state, container, false);
    }
}
