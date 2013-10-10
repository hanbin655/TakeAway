package com.foodie.service;


import org.springframework.stereotype.Service;

import com.foodie.model.Location;
import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.foodie.model.People;
import com.foodie.model.Restaurant;
import com.foodie.repository.MenuDAO;
import com.foodie.repository.MenuDAOImpl;
import com.foodie.repository.PeopleDAO;
import com.foodie.repository.PeopleDAOImpl;
import com.foodie.repository.RestaurantDAO;
import com.foodie.repository.RestaurantDAOImpl;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class DataStoreSetup{

	
	public static void  setup(){
		
		RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
		
		PeopleDAO peopleDAO = new PeopleDAOImpl();
		
		peopleDAO.add(new People("Bin","Han"));
		peopleDAO.add(new People("Yan","Li"));
		peopleDAO.add(new People("Jun","Ze"));
		peopleDAO.add(new People("Test1","Test2"));

		
		Location location = new Location(TestHelper.cst_Prvnc1,
    			TestHelper.cst_Ct1,TestHelper.cst_Zpcd1,
    			TestHelper.cst_Addrss1_1,TestHelper.cst_Addrss2_1);
    	Restaurant r = new Restaurant(TestHelper.cst_Name1,
    			TestHelper.cst_Dscrpt1,location);
    	restaurantDAO.add(r);
    	
    	Key key = r.getRestaurantId();
    	
    	Menu menu = new Menu(TestHelper.cst_MenuName1,
    			TestHelper.cst_MenuDscrpt1);
    	
    	
    	for(int i = 0; i<2; i++){
    		menu.addMenuItem(new MenuItem(TestHelper.cst_MnItmNm[i],
    				TestHelper.cst_MnItmPrc[i]));
    	}
    	
    	restaurantDAO.addMenu(key, menu);
    	
	}	
		
}
