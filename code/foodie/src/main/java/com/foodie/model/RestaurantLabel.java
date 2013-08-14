package com.foodie.model;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class RestaurantLabel {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key restaurantLabelId;
	@Persistent
	private Set<Key> restaurantIds = new HashSet<Key>();
	@Persistent
	private Key labelId;
	
	public Key getRestaurantLabelId() {
		return restaurantLabelId;
	}
	public void setRestaurantLabelId(Key restaurantLabelId) {
		this.restaurantLabelId = restaurantLabelId;
	}
	public Key getLabelId() {
		return labelId;
	}
	public void setLabelId(Key labelId) {
		this.labelId = labelId;
	}
}
