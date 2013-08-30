package com.foodie.yan;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class State {

	private String stateName;
	public String getState(){
		return stateName;
	}
	
	public State(String stateName){
		this.stateName = stateName;
	}
	
	public Boolean transfer(String url,HashMap<String,Object> paramMap) 
			throws ParserConfigurationException, SAXException,
			IOException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		HashMap<String,APIRule> ruleMap = null;
		ruleMap = XmlRuleParser.getInstance().get(stateName);
		if(ruleMap == null){
			return false;
		}
		
		HashMap<String,Method> methodList = null;
		methodList = XmlMethodParser.getInstance();
		if(methodList == null ){
			return false;
		}
		
		APIRule stateTransferRule = null;
		stateTransferRule = ruleMap.get(url);
		if(stateTransferRule == null ){
			return false;
		}
		
		
		for(String methodName : stateTransferRule.statements){
			Method methodMyType = methodList.get(methodName);
			Object object = Class.forName(methodMyType.type).newInstance();
			
			int paramNum = methodMyType.paramType.size();
			Class<?>[] paramTypeArray = new Class<?>[paramNum];
			Object [] paramValueArray = new Object[paramNum];
			for(int i = 0; i< paramNum ; i++){
				paramTypeArray[i] = Class.forName(methodMyType.paramType.get(i));
				paramValueArray[i] = paramMap.get(methodMyType.paramName.get(i));
			}
			java.lang.reflect.Method method = object.getClass().getDeclaredMethod(methodName, paramTypeArray);
			Object returnValue = method.invoke(object,paramValueArray);
			if(!(Boolean)returnValue){
				stateName = stateTransferRule.endState[0];
				return false;
			}
		}
		
		stateName = stateTransferRule.endState[1];
		return true;
	}
}
