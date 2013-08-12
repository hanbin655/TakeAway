package com.foodie.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class People {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String forename;
	
	@Persistent
	private String surname;
	
	@NotPersistent
	private String fullname;
 
	
	public People(String forname, String surname){
		this.setForename(forname);
		this.setSurname(surname);		
	}
	
	public Key getKey(){
		return key;
	}
	public String getForename() {
		return this.forename;
	}

	public String getSurname() {
		return this.surname;
	}
	
	public String getFullname() {
		return this.fullname;
	}
	
	public void setFullname() {
		this.fullname = this.forename + " " + this.surname;		
	}
	
	public void setForename(String value) {
		this.forename = value;
		setFullname();
	}

	public void setSurname(String value) {
		this.surname = value;
		setFullname();
	}

}
