package com.arandasebastian.appdiscover.controller;

import com.arandasebastian.appdiscover.ResultListener;
import com.arandasebastian.appdiscover.model.Track;
import com.arandasebastian.appdiscover.model.TrackDAO;

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