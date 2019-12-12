package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.ArtistsController;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText editText = findViewById(R.id.edt_search);
        Button button = findViewById(R.id.btn_search);
        RecyclerView recyclerView = findViewById(R.id.search_artist_recycler);

        final ArtistsController artistsController = new ArtistsController();
        final TrackAdapter trackAdapter = new TrackAdapter();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                artistsController.getArtistTracksByName(new ResultListener<List<Track>>() {
                    @Override
                    public void finish(List<Track> result) {
                        trackAdapter.setTrackList(result);
                        trackAdapter.notifyDataSetChanged();
                    }
                }, editText.getText().toString());

            }
        });
        recyclerView.setAdapter(trackAdapter);
    }

}
