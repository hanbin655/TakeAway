package com.foodie.repository;

import com.foodie.model.Location;
import com.google.appengine.api.datastore.Key;

import java.util.List;

public interface AddressDAO {
    public void setAddress(Key sessionId, Location address);
    public List<Location> getAddressByUserId(Key userId);
    public List<Location> getAddressByRestaurantId(Key restaurantId);
    public Location getAddressById(Key addressId);

}
