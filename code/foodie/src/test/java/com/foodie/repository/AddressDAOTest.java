package com.foodie.repository;

import junit.framework.Assert;
import com.foodie.BaseTest;
import com.foodie.model.Location;
import com.foodie.model.Restaurant;
import com.foodie.model.User;
import com.foodie.model.session.Session;
import com.google.appengine.api.datastore.KeyFactory;
import org.junit.Test;

import java.util.List;

public class AddressDAOTest extends BaseTest {
    private AddressDAO addressDAO = new AddressDAOImpl();

    @Test
    public void testGetAddressById() {
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        this.pmf.makePersistent(loc);
        Assert.assertEquals(city, addressDAO.getAddressById(loc.getLocationId()).getCity());

    }

    @Test
    public void testSetAddress() {
        User user = new User("JimWu", null, null, null, null);
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        user.getDeliveryAddresses().add(loc);
        pmf.makePersistent(user);
        Session session = new Session();
        session.setUserId(KeyFactory.keyToString(user.getUserId()));
        pmf.makePersistent(session);
        Location loc2 = new Location();
        String city2 = "amoi";
        loc2.setCity(city2);
        addressDAO.setAddress(session.getSessionId(), loc2);
        Assert.assertEquals(city2, PMF.get().getPersistenceManager().getObjectById(User.class, user.getUserId()).getDeliveryAddresses().get(1).getCity());

    }

    @Test
    public void testGetAddressByUserId() {
        User user = new User("JimWu", null, null, null, null);
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        user.getDeliveryAddresses().add(loc);
        pmf.makePersistent(user);
        Assert.assertEquals(1, addressDAO.getAddressByUserId(user.getUserId()).size());
        Assert.assertEquals(city, addressDAO.getAddressByUserId(user.getUserId()).get(0).getCity());

    }

    @Test
    public void testGetAddressByRestaurantId() {
        Restaurant restaurant = new Restaurant("jj", "jimJay", null);
        pmf.makePersistent(restaurant);
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        restaurant.setLocation(loc);
        pmf.makePersistent(restaurant);
        List<Location> locs = addressDAO.getAddressByRestaurantId(restaurant.getRestaurantId());
        Assert.assertEquals(city, locs.get(0).getCity());
        locs.get(0).setCity("amoi");
        pmf.makePersistent(locs.get(0));
        Assert.assertEquals("amoi", addressDAO.getAddressByRestaurantId(restaurant.getRestaurantId()).get(0).getCity());

    }

}
