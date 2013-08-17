package com.foodie.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@PersistenceCapable
public class Location {

	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key locationId;
	
	@Persistent
	private String province;
	
	@Persistent
	private String city;
	
	@Persistent
	private String zipcode;
	
	@Persistent
	private String address1;
	
	@Persistent
	private String address2;
	
	@Persistent
	private Key ownerId;
	
	public Location(String province, String city, String zipcode, 
			String address1, String address2){
		this.province = province;
		this.city = city;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		
	}
	
	public String getLocationId(){
		return KeyFactory.keyToString(locationId);
	}
	
	public String getProvince(){
		return province;
	}
	
	public void setProvince(String province){
		this.province = province;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getZipcode(){
		return zipcode;
	}
	
	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}
	
	public String getAddress1(){
		return address1;
	}
	
	public void setAddress1(String address1){
		this.address1 = address1;
	}

	public String getAddress2(){
		return address1;
	}
	
	public void setAddress2(String address2){
		this.address2 = address2;
	}

	public Key getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Key ownerId) {
		this.ownerId = ownerId;
	}



}
