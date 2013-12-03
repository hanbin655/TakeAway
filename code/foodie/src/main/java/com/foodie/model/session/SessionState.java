package com.foodie.model.session;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.foodie.service.xmlparser.XmlMethodParser;
import com.foodie.service.xmlparser.XmlRuleParser;

public class SessionState {

	private String stateName;
	public String getState(){
		return stateName;
	}
	
	//TODO: check if the state name is valid
	public SessionState(String stateName){
		this.stateName = stateName;
	}
	
	//TODO: Too many exceptions thrown
	public Boolean transfer(String url,HashMap<String,Object> paramMap) 
			throws ParserConfigurationException, SAXException,
			IOException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		
		XmlMethodParser methodParser = XmlMethodParser.getInstance();
		if(methodParser == null ){
			return false;
		}
		XmlRuleParser ruleParser = XmlRuleParser.getInstance();
		APIRule stateTransferRule = ruleParser.getAPIRule(stateName, url);
		
		if(stateTransferRule == null ){
			return false;
		}
		
		
		for(String methodName : stateTransferRule.statements){
			
			ValidationMethod validationMethod = methodParser.getMethod(methodName);
			
			int paramNum = validationMethod.paramType.size();
			System.out.println(paramNum);
			Object [] paramValueArray = new Object[paramNum];
			for(int i = 0; i< paramNum ; i++){
				paramValueArray[i] = paramMap.get(validationMethod.paramName.get(i));
			}
			Boolean returnValue = validationMethod.validate(paramValueArray);
			if(!returnValue){
				stateName = stateTransferRule.endState[0];
				return false;
			}
		}
		
		stateName = stateTransferRule.endState[1];
		return true;
	}
}
