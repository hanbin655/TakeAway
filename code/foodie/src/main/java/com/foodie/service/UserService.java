package com.foodie.service;

import com.foodie.model.User;
import com.foodie.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jim.wu
 * @date 11/25/13
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDao;

    public boolean register(String userName, String password) {
        if (userDao.getUserByUserName(userName) != null) return false;
        User user = new User(userName, password);
        userDao.persist(user);
        return true;


    }

}
