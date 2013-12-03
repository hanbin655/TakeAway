package com.foodie.model.session;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

public class ValidationMethod {
	public String name;
	public String type;
	
	public LinkedList<String> paramName = new LinkedList<String>();
	public LinkedList<String> paramType = new LinkedList<String>();

	public Boolean validate(Object [] paramValueArray) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Boolean returnValue = null;
		int paramNum =  paramName.size();
		
		Object object = Class.forName(type).newInstance();
		Class<?>[] paramTypeArray = new Class<?>[paramNum];
		for(int i = 0; i< paramNum ; i++){
			paramTypeArray[i] = Class.forName(paramType.get(i));
		}
		java.lang.reflect.Method method = object.getClass().getDeclaredMethod(
				name, paramTypeArray);
		returnValue = (Boolean) method.invoke(object,paramValueArray);
		
		return returnValue;
	}
}
