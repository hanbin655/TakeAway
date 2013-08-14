package com.foodie.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foodie.repository.MenuDAO;
import com.foodie.repository.MenuDAOImpl;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class MenuDAOTest {
	private final LocalServiceTestHelper helper =  
		        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());  

	private MenuDAO menuDAO = new MenuDAOImpl();
    
	@Before  
    public void setUp() {  
        helper.setUp();          
    }  
  
    @After  
    public void tearDown() {  
  //      helper.tearDown();  
    } 
	@Test
	public void createNewMenuTest(){
		Menu menu = new Menu("Menu1","TestMenu");
		menuDAO.add(menu);
		Key menuId = menu.getMenuId();
		Menu menuTest = menuDAO.getMenuById(menuId);
		assertEquals(menu.getDescription(), menuTest.getDescription());
		assertEquals(menu.getMenuName(),menuTest.getMenuName());
		
	}
	
	
	@Test
	public void addMenuItemTest(){
		Menu menu = new Menu("Menu2","TestMenu2");
		MenuItem menuitem1 = new MenuItem("item1", BigDecimal.valueOf(1.1));
		MenuItem menuitem2 = new MenuItem("item2", BigDecimal.valueOf(2,1));
		menu.addMenuItem(menuitem1);
		menu.addMenuItem(menuitem2);
		menuDAO.add(menu);
		Key menuId = menu.getMenuId();
		
		List<MenuItem> menuItems = menuDAO.getAllMenuItems(menuId);
		assertNotNull(menuItems);
		assertEquals(menuItems.size(),2);
		System.out.println(menuItems.get(0).getItemPrice());
//		assertEquals(menuitem1.getItemName(),menuItems.get(0).getItemName());
	//	assertEquals(menuitem2.getItemName(),menuItems.get(1).getItemName());
	}
}
