package com.foodie.model;

import java.math.BigDecimal;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class OrderItem {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key orderItemId;
	
	@Persistent
	private BigDecimal itemPrice;

	public Key getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Key orderItemId) {
		this.orderItemId = orderItemId;
	}
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	/*
	public Key getOrderId() {
		return orderId;
	}
	public void setOrderId(Key orderId) {
		this.orderId = orderId;
	}*/

}
