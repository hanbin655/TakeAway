package com.foodie.service;

import java.util.List;

import com.foodie.model.Location;
import com.foodie.model.Menu;
import com.foodie.model.Restaurant;
import com.google.appengine.api.datastore.Key;

public class PublicAccessService extends AbstractService {

	public List<Restaurant> getRestaurant(Location location, int bufferSize) 
			throws NullPointerException{
		return null;
	}
	
	public Restaurant getRestaurantById(Key restaurantId)
		throws NullPointerException{
		return null;
	}
	
	public List<Menu> getMenu(Key restaurantId) throws NullPointerException{
		return null;
	}
}
