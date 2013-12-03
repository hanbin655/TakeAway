package com.foodie.service;

import com.foodie.model.Location;

public class ValidateService {
	public Boolean isLogin(String sessionId){
		return true;
	}
	
	public Boolean isValidAddress(String sessionId, Location address){
		return true;
	}
	
	public Boolean isValidAddressId(String sessionId, String addressId){
		return true;
	}
}
