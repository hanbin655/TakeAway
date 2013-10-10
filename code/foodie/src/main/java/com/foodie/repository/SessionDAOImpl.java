package com.foodie.repository;

import com.foodie.model.session.Session;
import com.foodie.repository.annotation.JdoOperation;
import com.google.appengine.api.datastore.Key;

import org.springframework.stereotype.Repository;

@Repository
public class SessionDAOImpl extends BaseDAO implements SessionDAO {

    @JdoOperation
    @Override
    public Session getSessionById(Key sessionId) {
        return pm.detachCopy(pm.getObjectById(Session.class, sessionId));
    }

    @JdoOperation
    @Override
    public void persist(Session session) {
        pm.makePersistent(session);
    }
    
    
}
