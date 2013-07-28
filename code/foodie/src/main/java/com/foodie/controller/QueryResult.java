package com.foodie.controller;

public class QueryResult<T> {	
	public boolean success;
    public T data;

	private void setSuccess(boolean value){
		this.success = value;
	}

    private void setData(T data){
        this.data = data;
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
