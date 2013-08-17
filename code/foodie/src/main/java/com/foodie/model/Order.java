package com.foodie.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Order {

	public final int STATE_PENDING = 1;
	public final int STATE_COOKING = 2;
	public final int STATE_DELIVERING = 3;
	public final int STATE_FINISH = 0;
	
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key orderId;

	@Persistent
	private Key userId;
	
	@Persistent
	@Unowned
	private Location location;
	
	@Persistent
	private Key restaurantId;
	
	@Persistent
	private Date dateTime;
	
	@Persistent
	private String personName;
	
	@Persistent
	private List<OrderItem> orderItems; 
	
	@Persistent
	private int state;
	
	public String getOrderId() {
		return KeyFactory.keyToString(orderId);
	}
	public void setOrderId(Key orderId) {
		this.orderId = orderId;
	}
	public Key getUserId() {
		return userId;
	}
	public void setUserId(Key userId) {
		this.userId = userId;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Key getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Key restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
