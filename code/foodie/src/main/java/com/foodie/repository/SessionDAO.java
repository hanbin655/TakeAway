package com.foodie.repository;

import com.foodie.model.session.Session;
import com.google.appengine.api.datastore.Key;

public interface SessionDAO {
    public Session getSessionById(Key sessionId);
    public void persist(Session session);

}
