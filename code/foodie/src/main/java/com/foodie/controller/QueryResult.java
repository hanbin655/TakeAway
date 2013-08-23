package com.foodie.controller;

public class QueryResult<T> {	
	public boolean success;
    public T data;
    public String message;

	private void setSuccess(boolean value){
		this.success = value;
	}

    private void setData(T data){
        this.data = data;
    }
    
    public void setMessage(String message){
    	this.message = message;
    }
    
	
	public QueryResult(boolean success, T data){
		setSuccess(success);
        setData(data);
	}
		
	public static QueryResult<?> CreateFromSuccess(Object result){
        return new QueryResult<Object>(true, result);
	}
	public static QueryResult<?> CreateFromFailure(String exceptionString){
		return new QueryResult<Object>(false, exceptionString);
	}
	
}
