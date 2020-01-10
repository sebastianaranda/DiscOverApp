package com.arandasebastian.discoverapp.controller;

import com.arandasebastian.discoverapp.ResultListener;
import com.arandasebastian.discoverapp.model.Track;
import com.arandasebastian.discoverapp.model.TrackDAO;

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