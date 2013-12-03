package com.foodie.model;

import java.util.Calendar;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class BusinessHour {

	public final int SUNDAY = 0;
	public final int MONDAY = 1;
	public final int TUESDAY = 2;
	public final int WEDNESDAY = 3;
	public final int THURSDAY = 4;
	public final int FRIDAY = 5;
	public final int SATURDAY = 6;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key businessHourId;
	
	@Persistent
	private int[] openDays;
	
	//8:00 = 800 11:00 = 1100
	@Persistent
	private int fromTime;
	
	@Persistent
	private int toTime;
	
	
	//TODO: add validation
	public BusinessHour(int[] openDays,int fromTime, int toTime){
		this.openDays = openDays;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}
	
	public int[] getOpenDays(){
		return openDays;
	}
	
	public void setOpenDays(int[] openDays){
		this.openDays = openDays;
	}
	
	public void setOpenHours(int fromTime, int toTime){
		this.fromTime = fromTime;
		this.toTime = toTime;
	}
	public int getFromTime(){
		return fromTime;
	}
	
	public int getToTime(){
		return toTime;
	}
	
	public Boolean isOpen(){
		return isOpen(Calendar.getInstance());
	}
	
	public Boolean isOpen(Calendar c){
		for(int i:openDays){
			if(i==c.get(Calendar.DAY_OF_WEEK)){
				int time = c.get(Calendar.HOUR_OF_DAY);
				time *= 100;
				time += c.get(Calendar.MINUTE);
				if(time>fromTime && time<toTime){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

	public String getBusinessHourId() {
		return KeyFactory.keyToString(businessHourId);
	}

	public void setBusinessHourId(Key businessHourId) {
		this.businessHourId = businessHourId;
	}

	public void setFromTime(int fromTime) {
		this.fromTime = fromTime;
	}

	public void setToTime(int toTime) {
		this.toTime = toTime;
	}
}
