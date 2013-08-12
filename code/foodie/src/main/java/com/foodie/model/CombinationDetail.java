package com.foodie.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

//TODO: this class is to be deleted
public class CombinationDetail {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key combinationDetailId;
	@Persistent
	private Key menuItemId;
	@Persistent
	private Key cmbntnId;
	public Key getCombinationDetailId() {
		return combinationDetailId;
	}
	public void setCombinationDetailId(Key combinationDetailId) {
		this.combinationDetailId = combinationDetailId;
	}
	public Key getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Key menuItemId) {
		this.menuItemId = menuItemId;
	}
	public Key getCmbntnId() {
		return cmbntnId;
	}
	public void setCmbntnId(Key cmbntnId) {
		this.cmbntnId = cmbntnId;
	}

}
