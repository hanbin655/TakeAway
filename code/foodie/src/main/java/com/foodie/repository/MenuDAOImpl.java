package com.foodie.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.annotations.Transactional;

import org.datanucleus.FetchGroup;
import org.springframework.stereotype.Repository;

import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.google.appengine.api.datastore.Key;

@Repository
public class MenuDAOImpl implements MenuDAO {

	private PersistenceManagerFactory pmf;
	private String cst_getAllQuery = 
			"select from " + Menu.class.getName();
	public MenuDAOImpl(){
		pmf = PMF.get();
	}
	
	public Menu getMenuById(Key menuId){
		PersistenceManager pm = pmf.getPersistenceManager();
		Menu menu = null;
		try{
			menu = pm.getObjectById(Menu.class, menuId);
		}finally{
			pm.close();
		}
		return menu;
	}

	//TODO: workaround
	public List<MenuItem> getAllMenuItems(Key menuId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		List<MenuItem> menuItems = null;
		try{
			Menu menu = pm.getObjectById(Menu.class	, menuId);
			menuItems = menu.getMenuItems();
			for(MenuItem m :menuItems){
				m.getItemName();
			}
		}finally{
			pm.close();
		}
		return menuItems;
	}

	@Override
	public void add(Menu menu) {
		PersistenceManager pm = pmf.getPersistenceManager();
		try{
			pm.makePersistent(menu);
		}finally{
			pm.close();
		}
	}

	@Override
	public void addMenuItem(Key id, MenuItem menuItem) {
		PersistenceManager pm = pmf.getPersistenceManager();
		try{
			Menu menu= pm.getObjectById(Menu.class, id);
			menu.addMenuItem(menuItem);
		}finally{
			pm.close();
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getAllMenu() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		List<Menu> menus = null;
		try{
			menus = (List<Menu>) pm.newQuery(cst_getAllQuery).execute();
		}finally{
			pm.close();
		}
		return menus;
	}

	@Override
	public MenuItem getMenuItemById(Key menuItemId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		MenuItem menuItem = null;
		try{
			menuItem = pm.getObjectById(MenuItem.class, menuItemId);
		}finally{
			pm.close();
		}
		return menuItem;
	}
	
	

}
