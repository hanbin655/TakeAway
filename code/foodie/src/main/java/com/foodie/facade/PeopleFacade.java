package com.foodie.facade;

import com.foodie.model.People;



public class PeopleFacade {
	
	private String forename;
	private String surname;
	private String fullname;
 
	
	public PeopleFacade(String forname, String surname){
		this.setForename(forname);
		this.setSurname(surname);		
	}
	
	public PeopleFacade(People person){
		this.setForename(person.getForename());
		this.setSurname(person.getSurname());		
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
