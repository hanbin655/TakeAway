package com.foodie.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	@Persistent(mappedBy = "resaurantId")
	private List<Menu> menues = new ArrayList<Menu>();
	
	@Persistent
	private Set<Key> labels = new HashSet<Key>();

	public Key getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Key restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BusinessHour getBusinessHour() {
		return businessHour;
	}

	public void setBusinessHour(BusinessHour businessHour) {
		this.businessHour = businessHour;
	}

	public float getDeliveryDistance() {
		return deliveryDistance;
	}

	public void setDeliveryDistance(float deliveryDistance) {
		this.deliveryDistance = deliveryDistance;
	}

	public Key getCurrentMenuId() {
		return currentMenuId;
	}

	public void setCurrentMenuId(Key currentMenuId) {
		this.currentMenuId = currentMenuId;
	}

	public List<Menu> getMenues() {
		return menues;
	}

	public void setMenues(List<Menu> menues) {
		this.menues = menues;
	}
	
}
