package com.foodie.model;

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
	private Key restaurantId;
	@Persistent
	private Key labelId;
	public Key getRestaurantLabelId() {
		return restaurantLabelId;
	}
	public void setRestaurantLabelId(Key restaurantLabelId) {
		this.restaurantLabelId = restaurantLabelId;
	}
	public Key getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Key restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Key getLabelId() {
		return labelId;
	}
	public void setLabelId(Key labelId) {
		this.labelId = labelId;
	}
}
