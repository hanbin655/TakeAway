package com.foodie.repository;

import com.foodie.BaseSpringDAOTest;
import com.foodie.model.session.Session;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;

public class SessionDAOTest extends BaseSpringDAOTest {
    @Autowired
    private SessionDAO sessionDAO;
    

    @Test
    public void testGetSessionBySessionId() {
        Session session = new Session();
        session.setUserId("joker");
        pm.makePersistent(session);
        Assert.assertEquals("joker", sessionDAO.getSessionById(session.getSessionId()).getUserId());
        
        
    }

}
