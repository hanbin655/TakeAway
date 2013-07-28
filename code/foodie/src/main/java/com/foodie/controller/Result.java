package com.foodie.controller;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Result {	
	public boolean success;    

	private void setSuccess(boolean value){
		this.success = value;
	}   
	
	public Result(boolean success){
		setSuccess(success);       
	}
	
	public static Result CreateFromSuccess(Class<?> clazz, String method, Object[] parameters){
		try
		{			
			ArrayList<Class<?>> argList = new ArrayList<Class<?>>();
			Class<?>[] argTypes;
			
			if(parameters != null && parameters.length > 0)
			{
				for(Object p : parameters)
				{
					argList.add(p.getClass());
				}
				
				argTypes = (Class<?>[]) argList.toArray();
				
			} else {
				argTypes = new Class<?>[0];
			}
			
			Method m = clazz.getMethod(method, argTypes);
			
			if (Modifier.isStatic(m.getModifiers()))
			{
				m.invoke (null, parameters);
			} else 
			{
				m.invoke (clazz.newInstance(), parameters);
			}		
			
			return new Result(true);
		}
		catch(Exception e)
		{
			return new  Result(false);
		}
		
		
	}
		
	public static Result CreateFromSuccess(Class<?> staticClass, String method){		
		return CreateFromSuccess(staticClass, method, null);		
	}
	
	public static Result CreateFromSuccess(boolean result){		
		return new Result(result);		
	}
	
	
	public static Result CreateFromFailure(Object object){
		return new Result(false);
	}


	
}
