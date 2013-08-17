package com.foodie.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Label {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key labelId;
	
	@Persistent
	private String labelName;
	
	@Persistent
	private String description;
	
	//TODO: Not sure if we will use setter and getter here
	public String getLabelId() {
		return KeyFactory.keyToString(labelId);
	}

	public void setLabelId(Key labelId) {
		this.labelId = labelId;
	}

	public Label(String labelName, String description){
		this.labelName = labelName;
		this.description = description;
	}
	
	public String getLabelName(){
		return labelName;
	}
	
	public void setLabelName(String labelName){
		this.labelName = labelName;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
}
