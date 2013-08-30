package com.foodie.yan;

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

public class XmlMethodParser {

	private static HashMap<String,Method> methodMap;
	
	
	public static HashMap<String, Method> getInstance() 
			throws ParserConfigurationException, SAXException, IOException{
		if(methodMap == null){
			methodMap = new HashMap<String, Method>();
			File fXmlFile = new File("src/main/resources/yantest/methodList.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList methodNodeList = doc.getElementsByTagName("method");
			
			for(int i=0; i<methodNodeList.getLength(); i++){
				Method method = new Method();
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
		return methodMap;
	}
}
