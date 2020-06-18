package com.arandasebastian.discover.controller;

import com.arandasebastian.discover.ResultListener;
import com.arandasebastian.discover.model.Track;
import com.arandasebastian.discover.model.TrackDAO;

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