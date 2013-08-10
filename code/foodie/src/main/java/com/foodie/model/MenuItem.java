package com.foodie.model;

import java.math.BigDecimal;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class MenuItem {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key menuItemId;
	@Persistent
	private String itemName;
	@Persistent
	private Key menuId;
	@Persistent
	private BigDecimal itemPrice;
	@Persistent
	private String imageUrl;
	public Key getMenuItemId() {
		return menuItemId;
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
	public Key getMenuId() {
		return menuId;
	}
	public void setMenuId(Key menuId) {
		this.menuId = menuId;
	}
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
