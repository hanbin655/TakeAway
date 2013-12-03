package com.foodie.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Combination {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key combinationId;
	
	@Persistent
	private BigDecimal price;
	
	@Persistent
	private String description;

	@Persistent
	private Set<MenuItem> itemCombination;
	
	public String getCombinationId(){
		return KeyFactory.keyToString(combinationId);
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
