package com.foodie.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Order {

	public static final int STATE_PENDING = 1;
	public static int STATE_COOKING = 2;
	public static final int STATE_DELIVERING = 3;
	public static final int STATE_FINISH = 0;
	
    @PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key orderId;

	@Persistent
	private String userId;
	
	@Persistent
	@Unowned
	private Location location;
	
	@Persistent
	private String restaurantId;
	
	@Persistent
	private Date dateTime;
	
	@Persistent
	private String personName;
	
	@Persistent
	private List<OrderItem> orderItems; 
	
	@Persistent
	private int state;
	
	public Key getOrderId() {
		return orderId;
	}
	public void setOrderId(Key orderId) {
		this.orderId = orderId;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
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
    public static int getStateCooking() {
        return STATE_COOKING;
    }
    public static void setStateCooking(int stateCooking) {
        STATE_COOKING = stateCooking;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
	
	
}
