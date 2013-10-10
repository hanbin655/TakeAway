package com.foodie.controller;

import com.foodie.BaseSpringTest;
import com.foodie.model.Location;
import com.foodie.model.LoginInfo;
import com.foodie.model.Order;
import com.foodie.model.PaymentInfo;
import com.foodie.model.Restaurant;
import com.foodie.model.User;
import com.foodie.model.request.CreateNewUserRequest;
import com.foodie.model.session.Session;
import com.foodie.repository.AddressDAO;
import com.foodie.repository.MenuDAO;
import com.foodie.repository.OrderDAO;
import com.foodie.repository.PMF;
import com.foodie.repository.SessionDAO;
import com.foodie.repository.UserDAO;
import com.foodie.service.OrderService;
import com.foodie.util.json.JSONBinder;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import javax.jdo.PersistenceManager;

import junit.framework.Assert;

public class ApiControllerTest extends BaseSpringTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDAO orderDao;
    @Autowired
    UserDAO userDAO;
    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private AddressDAO addressDAO;
    private PersistenceManager pmf;
    @Autowired
    private ApiController apiController;

    @Before
    public void init() {
        super.init();
        pmf = PMF.get().getPersistenceManager();
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
                        .contentType(MediaType.APPLICATION_JSON));
        Assert.assertEquals(city2, PMF.get().getPersistenceManager().getObjectById(User.class, user.getUserId()).getDeliveryAddresses().get(1).getCity());

    }

    @Test
    public void testGetAddressById() throws Exception {
        Location loc = new Location();
        String city = "quanzhou";
        loc.setCity(city);
        pmf.makePersistent(loc);
        String jsonStr = mockMvc.perform(MockMvcRequestBuilders.get("/api/address/getAddressById/" + KeyFactory.keyToString(loc.getLocationId()))).andReturn()
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
        String jsonStr = mockMvc.perform(MockMvcRequestBuilders.get("/api/address/getAddressByUserId/" + KeyFactory.keyToString(user.getUserId()))).andReturn()
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
                .andReturn().getResponse().getContentAsString();
        JSONObject json = new JSONObject(jsonStr);
        Assert.assertEquals(city, json.getJSONArray("data").getJSONObject(0).getString("city"));

    }

    @Test
    public void testUTF_8() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/utf-8")).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testUserLogin() throws Exception {
        Session session = new Session();
        String password = "123456";
        String userName = "jim";
        User user = new User(userName, password, null, null, null);
        pmf.makePersistent(session);
        pmf.makePersistent(user);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(userName);
        loginInfo.setPassword(password);
        String jsonStr = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login").param("sessionId", KeyFactory.keyToString(session.getSessionId()))
                .content(JSONBinder.binder(LoginInfo.class).toJSON(loginInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        JSONObject json = new JSONObject(jsonStr);
        Assert.assertTrue(json.getBoolean("data"));
    }
    
    @Test
    public void testSetOrder() throws Exception {
        Session session = new Session();
        String password = "123456";
        String userName = "jim";
        User user = new User(userName, password, null, null, null);
        userDAO.persist(user);
        session.setUserId(KeyFactory.keyToString(user.getUserId()));
        sessionDAO.persist(session);
        Order order = new Order();
        order.setUserId(KeyFactory.keyToString(user.getUserId()));
        JSONBinder<Order> binder = JSONBinder.binder(Order.class);
        System.out.println(binder.fromJSON(binder.toJSON(order)));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order/setOrder").param("sessionId", KeyFactory.keyToString(session.getSessionId()))
                .content(JSONBinder.binder(Order.class).toJSON(order))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void testSetPaymentInfo() throws Exception {
        Session session = new Session();
        String password = "123456";
        String userName = "jim";
        User user = new User(userName, password, null, null, null);
        userDAO.persist(user);
        session.setUserId(KeyFactory.keyToString(user.getUserId()));
        sessionDAO.persist(session);
        Order order = new Order();
        order.setUserId(KeyFactory.keyToString(user.getUserId()));
        orderDao.persist(order);
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(KeyFactory.keyToString(order.getOrderId()));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/paymentInfo/setPaymentInfo").param("sessionId", KeyFactory.keyToString(session.getSessionId()))
                .content(JSONBinder.binder(PaymentInfo.class).toJSON(paymentInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
 
    @Test
    public void testCreateNewUser() throws Exception {
        Session session = new Session();
        String password = "123456";
        String userName = "jim";
        sessionDAO.persist(session);
        User user = new User(userName, password, null, null, null);
        Location location = new Location();
        String city = "quanzhou";
        location.setCity(city);
        CreateNewUserRequest request = new CreateNewUserRequest();
        request.setSessionId(KeyFactory.keyToString(session.getSessionId()));
        request.setUser(user);
        request.setLocation(location);
        JSONBinder<User> userBinder = JSONBinder.binder(User.class);
        System.out.println(userBinder.fromJSON(userBinder.toJSON(user)));
        JSONBinder<CreateNewUserRequest> binder = JSONBinder.binder(CreateNewUserRequest.class);
        System.out.println(binder.toJSON(request));
        System.out.println(binder.fromJSON(binder.toJSON(request)));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/createNewUser")
                .content(JSONBinder.binder(CreateNewUserRequest.class).toJSON(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
                
        session = pmf.getObjectById(Session.class, session.getSessionId());
        Assert.assertFalse(StringUtils.isEmpty(session.getUserId()));
        user = pmf.getObjectById(User.class, session.getUserId());
        Assert.assertEquals(userName, user.getUserName());
        Assert.assertEquals(city, user.getDeliveryAddresses().get(0).getCity());
        
    }

}
