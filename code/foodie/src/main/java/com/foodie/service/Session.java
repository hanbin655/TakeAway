package com.foodie.service;

import com.foodie.config.ApplicationHelper;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Session {

	private Key sessionId;
	
	private String userId;
	
	private SessionState state;
	
	private Boolean cookieSupport;
	

	public Session(){
		userId = ApplicationHelper.cst_Anonymous;
		cookieSupport = false;
	}
	public Session(Boolean cookieSupport){
		userId = ApplicationHelper.cst_Anonymous;
		cookieSupport = false;
		this.cookieSupport = cookieSupport;
	}
	public String getSessionId(){
		return KeyFactory.keyToString(sessionId);
	}
	
	public void setSessionId(Key sessionId){
		this.sessionId = sessionId;
	}
	
	public Boolean getCookieSupport(){
		return cookieSupport;
	}
	
	public void setCookieSupport(Boolean b){
		cookieSupport = b;
	}
}
