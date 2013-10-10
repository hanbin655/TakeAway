package com.foodie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.config.ApplicationHelper;
import com.foodie.model.Location;
import com.foodie.model.LoginInfo;
import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.foodie.model.Restaurant;
import com.foodie.model.User;
import com.foodie.model.session.Session;
import com.foodie.repository.MenuDAO;
import com.foodie.repository.RestaurantDAO;
import com.foodie.repository.SessionDAO;
import com.foodie.repository.UserDAO;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class PublicAccessService extends AbstractService {

    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RestaurantDAO restaurantDAO;

    // TODO: to be finished
    public List<Restaurant> getRestaurant(Location location, int bufferSize) throws NullPointerException {
        return null;
    }

    public Restaurant getRestaurantById(Key restaurantId) throws NullPointerException {
        Restaurant r = restaurantDAO.getRestaurantById(restaurantId);
        if (r == null) {
            this.throwException(new NullPointerException(ApplicationHelper.NULL_POINTER_EXCEPTION_MSG));
        }
        return r;
    }

    public List<Menu> getMenu(Key restaurantId) throws NullPointerException {
        List<Menu> menus = restaurantDAO.getMenu(restaurantId);
        if (menus.isEmpty()) {
            this.throwException(new NullPointerException(ApplicationHelper.NULL_POINTER_EXCEPTION_MSG));
        }
        return menus;
    }

    public Menu getMenuById(Key menuId) throws NullPointerException {
        Menu menu = menuDAO.getMenuById(menuId);
        if (menu == null) {
            this.throwException(new NullPointerException(ApplicationHelper.NULL_POINTER_EXCEPTION_MSG));
        }
        return menu;
    }

    public List<MenuItem> getMenuItem(Key menuId) throws NullPointerException {
        List<MenuItem> menuItems = menuDAO.getAllMenuItems(menuId);
        if (menuItems.isEmpty()) {
            this.throwException(new NullPointerException(ApplicationHelper.NULL_POINTER_EXCEPTION_MSG));
        }
        return menuItems;
    }

    public MenuItem getMenuItemById(Key menuItemId) throws NullPointerException {
        MenuItem menuItem = menuDAO.getMenuItemById(menuItemId);
        if (menuItem == null) {
            this.throwException(new NullPointerException(ApplicationHelper.NULL_POINTER_EXCEPTION_MSG));
        }

        return menuItem;
    }
    
    public boolean login(String sessionId, LoginInfo loginInfo) {
        Session session = sessionDAO.getSessionById(KeyFactory.stringToKey(sessionId));
        if (session  == null) return false;
        User user = userDAO.getUserByUserName(loginInfo.getUserName());
        if (user == null) return false;
        if (loginInfo.getPassword().equals(user.getPassword())) {
            session.setUserId(KeyFactory.keyToString(user.getUserId()));
            sessionDAO.persist(session);
            return true;
        }
        return false;
    }
    
    public String createNewUser(String sessionId, User user, Location location) {
        Session session = sessionDAO.getSessionById(KeyFactory.stringToKey(sessionId));
        if (session  == null) return null;
        user.addNewAddress(location);
        userDAO.persist(user);
        System.out.println(user.getUserId());
        session.setUserId(KeyFactory.keyToString(user.getUserId()));
        sessionDAO.persist(session);
        return KeyFactory.keyToString(user.getUserId());
    }
}
