package com.digitalhouse.a0819cpmoacn02armo_01.controller;

import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;
import com.digitalhouse.a0819cpmoacn02armo_01.model.Track;
import com.digitalhouse.a0819cpmoacn02armo_01.model.TrackDAO;

public class TrackController {

    public void getTrackByID(final ResultListener<Track> resultListener, Integer idTrack){
        TrackDAO trackDAO = new TrackDAO();
        trackDAO.getTrackByID(new ResultListener<Track>() {
            @Override
            public void finish(Track result) {
                resultListener.finish(result);
            }
        }, idTrack);
    }

}