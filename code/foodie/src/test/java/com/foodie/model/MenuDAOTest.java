package com.foodie.model;

import com.foodie.repository.MenuDAO;
import com.foodie.repository.MenuDAOImpl;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuDAOTest {
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setApplyAllHighRepJobPolicy());

    private final BigDecimal testValue1 = new BigDecimal(1.1);
    private final BigDecimal testValue2 = new BigDecimal(2.0);

    private MenuDAO menuDAO = new MenuDAOImpl();

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void createNewMenuTest() {
        Menu menu = new Menu("Menu1", "TestMenu");
        menuDAO.add(menu);
        Key menuId = KeyFactory.stringToKey(menu.getMenuId());
        Menu menuTest = menuDAO.getMenuById(menuId);
        assertEquals(menu.getDescription(), menuTest.getDescription());
        assertEquals(menu.getMenuName(), menuTest.getMenuName());

    }

    @Test
    public void addMenuItemTest() {
        Menu menu = new Menu("Menu2", "TestMenu2");
        MenuItem menuitem1 = new MenuItem("item1", testValue1);
        MenuItem menuitem2 = new MenuItem("item2", testValue2);
        menu.addMenuItem(menuitem1);
        menu.addMenuItem(menuitem2);
        menuDAO.add(menu);
        // Key menuId = menu.getMenuId();
        List<Menu> result1 = menuDAO.getAllMenu();
        Key menuId = KeyFactory.stringToKey(result1.get(0).getMenuId());
        List<MenuItem> menuItems = menuDAO.getAllMenuItems(menuId);
        assertNotNull(menuItems);
        assertEquals(menuItems.size(), 2);
        assertEquals(menuitem1.getItemName(), menuItems.get(0).getItemName());
        assertEquals(menuitem2.getItemName(), menuItems.get(1).getItemName());
        assertEquals(menuitem1.getItemPrice(), menuItems.get(0).getItemPrice());
        assertEquals(menuitem2.getItemPrice(), menuItems.get(1).getItemPrice());
    }
}
