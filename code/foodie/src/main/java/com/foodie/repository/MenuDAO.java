package com.foodie.repository;

import java.util.List;

import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.google.appengine.api.datastore.Key;

public interface MenuDAO {
	public void add(Menu menu);
	public void addMenuItem(Key id, MenuItem menuItem);
	public Menu getMenuById(Key menuId);
	public List<MenuItem> getAllMenuItems(Key id);
	public List<Menu> getAllMenu();
	public MenuItem getMenuItemById(Key menuItemId);
}
