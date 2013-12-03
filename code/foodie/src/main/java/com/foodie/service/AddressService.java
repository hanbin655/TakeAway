package com.foodie.service;

import com.foodie.model.Location;
import com.foodie.repository.AddressDAO;
import com.google.appengine.api.datastore.KeyFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressDAO addressDAO;

    public void setAddress(String sessionId, Location address) {
        addressDAO.setAddress(KeyFactory.stringToKey(sessionId), address);
    }

    public List<Location> getAddressByUserId(String userId) {
        return addressDAO.getAddressByUserId(KeyFactory.stringToKey(userId));
    }

    public List<Location> getAddressByRestaurantId(String restaurantId) {
        return addressDAO.getAddressByRestaurantId(KeyFactory.stringToKey(restaurantId));
    }

    public Location getAddressById(String addressId) {
        return addressDAO.getAddressById(KeyFactory.stringToKey(addressId));
    }

}
