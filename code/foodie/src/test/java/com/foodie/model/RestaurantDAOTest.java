package com.foodie.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foodie.repository.MenuDAO;
import com.foodie.repository.MenuDAOImpl;
import com.foodie.repository.RestaurantDAO;
import com.foodie.repository.RestaurantDAOImpl;
import com.foodie.service.TestHelper;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class RestaurantDAOTest {

    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
    private MenuDAO menuDAO = new MenuDAOImpl();

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void addRestuarantTest() {
        Location location = new Location(TestHelper.cst_Prvnc1, TestHelper.cst_Ct1, TestHelper.cst_Zpcd1, TestHelper.cst_Addrss1_1, TestHelper.cst_Addrss2_1);
        Restaurant r = new Restaurant(TestHelper.cst_Name1, TestHelper.cst_Dscrpt1, location);
        restaurantDAO.add(r);
        Key key = r.getRestaurantId();
        Restaurant rTest = restaurantDAO.getRestaurantById(key);

        assertEquals(r.getName(), rTest.getName());
        assertEquals(r.getDescription(), rTest.getDescription());
        assertEquals(r.getLocation().getProvince(), rTest.getLocation().getProvince());
        assertEquals(r.getLocation().getCity(), rTest.getLocation().getCity());
        assertEquals(r.getLocation().getZipcode(), rTest.getLocation().getZipcode());
        assertEquals(r.getLocation().getAddress1(), rTest.getLocation().getAddress1());
        assertEquals(r.getLocation().getAddress2(), rTest.getLocation().getAddress2());
    }

    @Test
    public void addMenuTest() {
        Location location = new Location(TestHelper.cst_Prvnc1, TestHelper.cst_Ct1, TestHelper.cst_Zpcd1, TestHelper.cst_Addrss1_1, TestHelper.cst_Addrss2_1);
        Restaurant r = new Restaurant(TestHelper.cst_Name1, TestHelper.cst_Dscrpt1, location);
        restaurantDAO.add(r);

        Key key = r.getRestaurantId();

        Menu menu = new Menu(TestHelper.cst_MenuName1, TestHelper.cst_MenuDscrpt1);

        for (int i = 0; i < 2; i++) {
            menu.addMenuItem(new MenuItem(TestHelper.cst_MnItmNm[i], TestHelper.cst_MnItmPrc[i]));
        }

        restaurantDAO.addMenu(key, menu);

        List<Menu> menuTest = restaurantDAO.getMenu(key);
        assertNotNull(menuTest);
        assertEquals(1, menuTest.size());

        Key menuId = KeyFactory.stringToKey(menuTest.get(0).getMenuId());
        Menu m = menuDAO.getMenuById(menuId);
        assertEquals(TestHelper.cst_MenuName1, m.getMenuName());
        assertEquals(TestHelper.cst_MenuDscrpt1, m.getDescription());

        List<MenuItem> menuItems = menuDAO.getAllMenuItems(menuId);
        assertEquals(menuItems.size(), 2);

        int i = 0;
        for (MenuItem menuItem : menuItems) {
            assertEquals(menuItem.getItemName(), TestHelper.cst_MnItmNm[i]);
            assertEquals(menuItem.getItemPrice(), TestHelper.cst_MnItmPrc[i++]);
        }
    }

}
