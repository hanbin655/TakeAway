package com.foodie.repository;

import com.foodie.model.Location;
import com.foodie.model.Restaurant;
import com.foodie.model.User;
import com.foodie.model.session.Session;
import com.foodie.util.json.JSONBinder;
import com.google.appengine.api.datastore.Key;

import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.annotations.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {

    private PersistenceManagerFactory pmf;

    public AddressDAOImpl() {
        pmf = PMF.get();
    }

    @Override
    @Transactional
    public void setAddress(Key sessionId, Location address) {
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            Session session = pm.getObjectById(Session.class, sessionId);
            if (session == null) return;
            User user = pm.getObjectById(User.class, session.getUserId());
            if (user == null) return;
            user.getDeliveryAddresses().add(address);
            System.out.println(JSONBinder.binder(User.class).toJSON(user));
        } finally {
            pm.close();
        }
    }

    @Override
    public List<Location> getAddressByUserId(Key userId) {
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            User user = pm.getObjectById(User.class, userId);
            List<Location> result = new ArrayList<Location>();
            List<Location> source = user.getDeliveryAddresses();
            for (Location loc : source) result.add(pm.detachCopy(loc));
            return result;
        } finally {
            pm.close();
        }
    }

    @Override
    public List<Location> getAddressByRestaurantId(Key restaurantId) {
        List<Location> locations = new ArrayList<Location>();
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            Restaurant restaurant = pm.getObjectById(Restaurant.class, restaurantId);
            locations.add(pm.detachCopy(restaurant.getLocation()));
            return locations;
        } finally {
            pm.close();
        }
    }

    @Override
    public Location getAddressById(Key addressId) {
        Location loc = null;
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            loc = pm.getObjectById(Location.class, addressId);
            return pm.detachCopy(loc);
        } finally {
            pm.close();
        }
    }


}
