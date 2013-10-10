package com.foodie.controller;

import com.foodie.config.AppConfig;
import com.foodie.model.Location;
import com.foodie.model.Restaurant;
import com.foodie.model.User;
import com.foodie.model.session.Session;
import com.foodie.repository.AddressDAO;
import com.foodie.repository.MenuDAO;
import com.foodie.repository.PMF;
import com.foodie.util.json.JSONBinder;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.jdo.PersistenceManager;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class ApiControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private AddressDAO addressDAO;
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private PersistenceManager pmf;

    protected MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        helper.setUp();
        pmf = PMF.get().getPersistenceManager();
    }

    @After
    public void dispose() {
        helper.tearDown();
    }

    @Test
    public void testSetMenu() throws Exception {

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
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/address/setAddress").param("sessionId", KeyFactory.keyToString(session.getSessionId())).content(JSONBinder.binder(Location.class).toJSON(loc2))
                        .contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        Assert.assertEquals(city2, PMF.get().getPersistenceManager().getObjectById(User.class, user.getUserId()).getDeliveryAddresses().get(1).getCity());

    }

    @Test
    public void testGetAddressById() throws Exception {
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        pmf.makePersistent(loc);
        String jsonStr = mockMvc.perform(MockMvcRequestBuilders.get("/api/address/getAddressById/" + KeyFactory.keyToString(loc.getLocationId()))).andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse().getContentAsString();
        JSONObject json = new JSONObject(jsonStr);
        Assert.assertEquals(city, json.getJSONObject("data").getString("city"));

    }

    @Test
    public void testGetAddressByUserId() throws Exception {
        User user = new User("JimWu", null, null, null, null);
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        user.getDeliveryAddresses().add(loc);
        pmf.makePersistent(user);
        String jsonStr = mockMvc.perform(MockMvcRequestBuilders.get("/api/address/getAddressByUserId/" + KeyFactory.keyToString(user.getUserId()))).andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse().getContentAsString();
        JSONObject json = new JSONObject(jsonStr);
        Assert.assertEquals(city, json.getJSONArray("data").getJSONObject(0).getString("city"));

    }

    @Test
    public void testGetAddressByRestaurantId() throws Exception {
        Restaurant restaurant = new Restaurant("jj", "jimJay", null);
        pmf.makePersistent(restaurant);
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        restaurant.setLocation(loc);
        pmf.makePersistent(restaurant);
        String jsonStr = mockMvc.perform(MockMvcRequestBuilders.get("/api/address/getAddressByRestaurantId/" + KeyFactory.keyToString(restaurant.getRestaurantId())))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        JSONObject json = new JSONObject(jsonStr);
        Assert.assertEquals(city, json.getJSONArray("data").getJSONObject(0).getString("city"));

    }

    @Test
    public void testUTF_8() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
