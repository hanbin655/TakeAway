package com.foodie.model.session;

import com.foodie.config.ApplicationHelper;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Session {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key sessionId;

    @Persistent
    private String userId;

    @Persistent
    private SessionState state;

    @Persistent
    private Boolean cookieSupport;

    public Session() {
        userId = ApplicationHelper.CST_ANONYMOUS;
        cookieSupport = false;
    }

    public Session(Boolean cookieSupport) {
        userId = ApplicationHelper.CST_ANONYMOUS;
        cookieSupport = false;
        this.cookieSupport = cookieSupport;
    }

    public Key getSessionId() {
        return sessionId;
    }

    public void setSessionId(Key sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getCookieSupport() {
        return cookieSupport;
    }

    public void setCookieSupport(Boolean b) {
        cookieSupport = b;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }
}
