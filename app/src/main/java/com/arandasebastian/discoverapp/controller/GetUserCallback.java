package com.arandasebastian.discoverapp.controller;

import android.net.Uri;
import com.arandasebastian.discoverapp.model.User;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUserCallback {

    private IGetUserResponse mGetUserResponse;
    private GraphRequest.Callback mCallback;

    public interface IGetUserResponse {
        void onCompleted(User user);
    }

    public GetUserCallback(final IGetUserResponse getUserResponse) {
        mGetUserResponse = getUserResponse;
        mCallback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                User user = null;
                try {
                    JSONObject userObj = response.getJSONObject();
                    if (userObj == null) {
                        return;
                    }
                    user = jsonToUser(userObj);

                } catch (JSONException e) {
                    // Handle exception ...
                }
                mGetUserResponse.onCompleted(user);
            }
        };
    }

    private User jsonToUser(JSONObject user) throws JSONException {
        Uri picture = Uri.parse(user.getJSONObject("picture").getJSONObject("data").getString("url"));
        String name = user.getString("name");
        String id = user.getString("id");
        String email = null;
        if (user.has("email")) {
            email = user.getString("email");
        }
        return new User(name, email);
    }

    public GraphRequest.Callback getCallback() {
        return mCallback;
    }

}
