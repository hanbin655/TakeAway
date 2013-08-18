package com.foodie.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.foodie.model.LocationData;
import com.foodie.model.Menu;
import com.foodie.model.Restaurant;
import com.google.appengine.api.datastore.Key;

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

	private PersistenceManagerFactory pmf;
	private String cst_getAllQuery = 
			"select from " + Restaurant.class.getName();
	
	public RestaurantDAOImpl(){
		pmf = PMF.get();
	}
	
	//TODO: should catch exceptions 
	@Override
	public void add(Restaurant r){
		PersistenceManager pm = pmf.getPersistenceManager();
		try{
			pm.makePersistent(r);
		}
		finally{
			pm.close();
		}
	}

	@Override
	public void addMenu(Key restaurantId, Menu m) {
		PersistenceManager pm = pmf.getPersistenceManager();
		try{
			Restaurant r = pm.getObjectById(Restaurant.class,restaurantId);
			r.addtMenu(m);
		}
		finally{
			pm.close();
		}
	}

	//TODO: add location comparison
	@SuppressWarnings("unchecked")
	@Override
	public List<Restaurant> getRestaurant(LocationData location, int bufferSize) {
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Restaurant> result = null;
		try{
			Query q = pm.newQuery(cst_getAllQuery);
			q.setRange(0, bufferSize);
			result = (List<Restaurant>)q.execute();
		}finally{
			pm.close();
		}
		return result;
	}

	@Override
	public Restaurant getRestaurantById(Key restaurantId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Restaurant r = null;
		try{
			r = pm.getObjectById(Restaurant.class, restaurantId);
			//Workaround to populate the Location object
			r.getLocation().getProvince();
		}finally{
			pm.close();
		}
		return r;
	}

	@Override
	public List<Menu> getMenu(Key restaurantId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Menu> result = null;
		try{
			Restaurant r = pm.getObjectById(Restaurant.class, restaurantId);
			result = r.getMenues();
		}finally{
			pm.close();
		}
		return result;
	}

}
