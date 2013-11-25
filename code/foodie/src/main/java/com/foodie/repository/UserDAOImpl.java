package com.foodie.repository;

import com.foodie.model.User;
import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;

import javax.jdo.Query;
import javax.jdo.annotations.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @SuppressWarnings("unchecked")
    @Override
    public User getUserByUserName(String userName) {
        Query query = pm.newQuery(User.class);
        query.setFilter("userName == userNameParam");
        query.declareParameters("String userNameParam");
        List<User> userList = ((List<User>) query.execute(userName));
        System.out.println("userList.size()" + userList.size());
        if (userList.isEmpty()) return null;
        return userList.get(0);
    }

    @Override
    public User getUserById(Key userId) {
        return pm.getObjectById(User.class, userId);
    }

    @Override
    @Transactional
    public void persist(User user) {
        pm.makePersistent(user);
    }

}
