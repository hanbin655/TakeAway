package com.foodie.repository;

import com.foodie.model.User;
import com.foodie.repository.annotation.JdoOperation;
import com.google.appengine.api.datastore.Key;

import org.springframework.stereotype.Repository;

import javax.jdo.Query;

import java.util.List;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @SuppressWarnings("unchecked")
    @JdoOperation
    @Override
    public User getUserByUserName(String userName) {
        Query query = pm.newQuery(User.class);
        query.setFilter("userName == userNameParam");
        query.declareParameters("String userNameParam");
        return ((List<User>)query.execute(userName)).get(0);
    }

    @JdoOperation
    @Override
    public User getUserById(Key userId) {
        return pm.getObjectById(User.class, userId);
    }

    @JdoOperation
    @Override
    public void persist(User user) {
        pm.makePersistent(user);
    }

}
