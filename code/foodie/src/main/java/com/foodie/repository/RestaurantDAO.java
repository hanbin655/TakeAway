package com.foodie.repository;

import java.util.List;

import com.foodie.model.LocationData;
import com.foodie.model.Menu;
import com.foodie.model.Restaurant;
import com.google.appengine.api.datastore.Key;

public interface RestaurantDAO {
	public void add(Restaurant r);
	public void addMenu(Key restaurantId,Menu m);
	public List<Restaurant> getRestaurant(LocationData location, int bufferSize);
	public Restaurant getRestaurantById(Key restaurantId);
	public List<Menu> getMenu(Key restaurantId);
	
}
