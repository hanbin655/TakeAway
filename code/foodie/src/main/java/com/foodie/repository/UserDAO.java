package com.foodie.repository;

import com.foodie.model.User;
import com.google.appengine.api.datastore.Key;

public interface UserDAO {
    
    public User getUserByUserName(String userName);
    public User getUserById(Key userId);
    public void persist(User user);
    

}
