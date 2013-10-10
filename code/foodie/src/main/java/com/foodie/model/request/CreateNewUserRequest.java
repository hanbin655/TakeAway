package com.foodie.model.request;

import com.foodie.model.Location;
import com.foodie.model.User;

public class CreateNewUserRequest {
    private Location location;
    private User user;
    private String sessionId;
    public Location getLocation() {
        return location;
    }
    public User getUser() {
        return user;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
