package com.digitalhouse.a0819cpmoacn02armo_01.model;

import android.net.Uri;

public class User {
    private final Uri picture;
    private final String name;
    private final String id;
    private final String email;

    public User(Uri picture, String name, String id, String email) {
        this.picture = picture;
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public Uri getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
