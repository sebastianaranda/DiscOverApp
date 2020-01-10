package com.arandasebastian.discoverapp.model;

public class User {
    private String userProfileImage;
    private String name;
    private String email;

    public User(){
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
