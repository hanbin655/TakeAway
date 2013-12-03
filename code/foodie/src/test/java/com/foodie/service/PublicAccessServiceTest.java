package com.foodie.service;

import com.foodie.BaseSpringDAOTest;
import com.foodie.model.Location;
import com.foodie.model.LoginInfo;
import com.foodie.model.User;
import com.foodie.model.session.Session;
import com.foodie.repository.SessionDAO;
import com.google.appengine.api.datastore.KeyFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import junit.framework.Assert;

public class PublicAccessServiceTest extends BaseSpringDAOTest {
    @Autowired
    private PublicAccessService publicAccessService;
    @Autowired
    private SessionDAO sessionDAO;
    @Test
    public void testLogin() {
        Session session = new Session();
        String password = "123456";
        String userName = "jim";
        User user = new User(userName, password, null, null, null);
        pm.makePersistent(session);
        pm.makePersistent(user);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(userName);
        loginInfo.setPassword(password);
        Assert.assertTrue(publicAccessService.login(KeyFactory.keyToString(session.getSessionId()), loginInfo));
    }
    @Test
    public void testCreateNewUser() {
        Session session = new Session();
        String password = "123456";
        String userName = "jim";
        sessionDAO.persist(session);
        User user = new User(userName, password, null, null, null);
        Location location = new Location();
        String city = "quanzhou";
        location.setCity(city);
        publicAccessService.createNewUser(KeyFactory.keyToString(session.getSessionId()), user, location);
        session = pm.getObjectById(Session.class, session.getSessionId());
        Assert.assertFalse(StringUtils.isEmpty(session.getUserId()));
        user = pm.getObjectById(User.class, session.getUserId());
        Assert.assertEquals(userName, user.getUserName());
        Assert.assertEquals(city, user.getDeliveryAddresses().get(0).getCity());
    }

}
