package com.example.testinglogin;

import android.graphics.drawable.Drawable;

public class Users {

    String userId, name, profile, email, location;


    public Users(String userId, String name, String profile, String email, String location) {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
        this.email = email;
        this.location= location;
    }

    public Users() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profile;
    }

    public Drawable setProfile(String profile) {
        this.profile = profile;
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
