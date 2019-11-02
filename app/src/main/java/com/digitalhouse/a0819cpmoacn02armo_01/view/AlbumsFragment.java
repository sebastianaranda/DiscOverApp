package com.digitalhouse.a0819cpmoacn02armo_01.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.digitalhouse.a0819cpmoacn02armo_01.R;

public class AlbumsFragment extends Fragment {

    public AlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_albums, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_albums_recycler);
        return view;
    }

}
