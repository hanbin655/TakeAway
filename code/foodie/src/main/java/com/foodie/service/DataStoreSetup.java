package com.foodie.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Service;

import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.foodie.model.People;
import com.foodie.repository.PMF;

@Service
public class DataStoreSetup{

	public static void  setup(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			
			List<People> result = new ArrayList<People>();
			result.add(new People("Bin","Han"));
			result.add(new People("Yan","Li"));
			result.add(new People("Jun","Ze"));
			result.add(new People("Test1","Test2"));

			pm.makePersistentAll(result);
			
			Menu menu = new Menu("Menu2","TestMenu2");
			MenuItem menuitem1 = new MenuItem("item1", BigDecimal.valueOf(1.1));
			MenuItem menuitem2 = new MenuItem("item2", BigDecimal.valueOf(2,1));
			menu.addMenuItem(menuitem1);
			menu.addMenuItem(menuitem2);
			pm.makePersistent(menu);
		}
		finally{
			pm.close();
		}
			

		
	}	
		
}
