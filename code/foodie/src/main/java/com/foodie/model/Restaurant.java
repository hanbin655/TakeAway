package com.foodie.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Restaurant {

	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key restaurantId;
	
	@Persistent
	private String name;
	
	@Persistent
	private String description;
	
	@Persistent
	private BusinessHour businessHour;

	
	@Persistent
	private float deliveryDistance;
	
	@Persistent
	private Key currentMenuId;
	
	@Persistent
	private List<Menu> menues;
	
}
