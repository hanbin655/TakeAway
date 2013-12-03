package com.foodie.service.xmlparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.foodie.model.session.ValidationMethod;

public class XmlMethodParser {

	private static String xmlLocation = "src/main/resources/session/methodList.xml";
	private HashMap<String,ValidationMethod> methodMap;
	private static XmlMethodParser xmlMethodParser = null;
	private boolean isLoaded = false;;
	public boolean isLoaded(){
		return isLoaded;
	}
	private XmlMethodParser(){
		methodMap = new HashMap<String, ValidationMethod>();
		try{
			readFromXml();
			isLoaded = true;
		}catch(Exception e){
			isLoaded = false;
		}
		
	}
	private void readFromXml() throws ParserConfigurationException,
	SAXException, IOException{
		if(!isLoaded){
			File fXmlFile = new File(xmlLocation);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList methodNodeList = doc.getElementsByTagName("method");
			
			for(int i=0; i<methodNodeList.getLength(); i++){
				ValidationMethod method = new ValidationMethod();
				Node methodNode = methodNodeList.item(i);
				
				String methodName = ((Element)methodNode).getElementsByTagName(
						"name").item(0).getFirstChild().getNodeValue();
				
				String methodClass = ((Element)methodNode).getElementsByTagName(
						"class").item(0).getFirstChild().getNodeValue();
				
				NodeList paramNodeList =  ((Element)methodNode).getElementsByTagName(
						"param");
				
				method.name = methodName;
				method.type = methodClass;
				
				for(int j = 0 ; j < paramNodeList.getLength(); j++){
					Node paramNode = paramNodeList.item(j);
					String paramName =  ((Element)paramNode).getElementsByTagName(
							"name").item(0).getFirstChild().getNodeValue();
					
					String paramType =  ((Element)paramNode).getElementsByTagName(
							"type").item(0).getFirstChild().getNodeValue();
					method.paramName.add(paramName);
					method.paramType.add(paramType);
				}
				
				methodMap.put(methodName, method);
			}
		}
	}
	public static XmlMethodParser getInstance() 
			throws ParserConfigurationException, SAXException, IOException{
		if(xmlMethodParser == null){
			xmlMethodParser = new XmlMethodParser();
		}
		return xmlMethodParser;
	}
	
	public ValidationMethod getMethod(String methodName){
		ValidationMethod method = null;
		if(isLoaded){
			method = methodMap.get(methodName);
		}
		return method;
	}
}
