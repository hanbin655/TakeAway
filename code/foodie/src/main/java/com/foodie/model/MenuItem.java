package com.foodie.model;

import java.math.BigDecimal;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class MenuItem {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key menuItemId;
	
	@Persistent
	private String itemName;
	
	/* No need for bidirectional relations
	@Persistent
	private Key menuId;
	*/
	@Persistent
	private BigDecimal itemPrice;
	@Persistent
	private String imageUrl;
	
	public MenuItem(String itemName, BigDecimal price){
		this.itemName = itemName;
		itemPrice = price;
	}
	
	public String getMenuItemId() {
		return KeyFactory.keyToString(menuItemId);
	}
	public void setMenuItemId(Key menuItemId) {
		this.menuItemId = menuItemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/*
	public Key getMenuId() {
		return menuId;
	}
	public void setMenuId(Key menuId) {
		this.menuId = menuId;
	}
	*/
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
