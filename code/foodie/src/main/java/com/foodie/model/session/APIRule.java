package com.foodie.model.session;

public class APIRule {

	public String url;
	public String[] statements;
	public String[] endState;
	
	public APIRule(String url,String[] statements, String[] endState){
		this.url = url;
		this.statements = statements.clone();
		this.endState = endState.clone();
	}
}
