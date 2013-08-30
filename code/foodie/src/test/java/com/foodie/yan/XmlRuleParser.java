package com.foodie.yan;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

public class XmlRuleParser {

	private static HashMap<String,HashMap<String,APIRule>> stateMap;
	
	public XmlRuleParser() {
	}
	
	public static HashMap<String,HashMap<String,APIRule>> getInstance() throws 
	ParserConfigurationException, SAXException, IOException{
		if(stateMap == null){
			stateMap = new HashMap<String,HashMap<String, APIRule>>();
			File fXmlFile = new File("src/main/resources/yantest/stateTransfer.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("state");
			
			for(int i=0;i<nList.getLength();i++){
				HashMap<String, APIRule> apiMap = new HashMap<String,APIRule>();
				
				Node stateNode = nList.item(i);
				Element e = (Element) stateNode;
				Node nameNode = (Node)e.getElementsByTagName("name").item(0);
				String stateName = nameNode.getFirstChild().getNodeValue();
				Node apiListNode = (Node)e.getElementsByTagName("api-list").item(0);
				Element apiListElement = (Element)apiListNode;
				NodeList nAPINode = apiListElement.getElementsByTagName("api");
				for(int j = 0; j<nAPINode.getLength();j++){
					Node apiNode = nAPINode.item(j);
					Element apiNodeElement = (Element) apiNode;
					
					Node urlNode = (Node)apiNodeElement.getElementsByTagName("url").item(0);
					String url = urlNode.getFirstChild().getNodeValue();
					
					NodeList statementNodeList = apiNodeElement.getElementsByTagName("statement");
					String[] statements = new String[statementNodeList.getLength()];
					for(int k = 0; k< statementNodeList.getLength();k++){
						statements[k] 
								= statementNodeList.item(k).getFirstChild().getNodeValue();
					}
					String[] endState = new String[2];
					Node falseEndNode = apiNodeElement.getElementsByTagName("false").item(0);
					endState[0] = falseEndNode.getFirstChild().getNodeValue();

					Node trueEndNode = apiNodeElement.getElementsByTagName("true").item(0);
					endState[1] = trueEndNode.getFirstChild().getNodeValue();
					
					APIRule tempAPIRule = new APIRule(url, statements,endState);
					apiMap.put(url, tempAPIRule);
				}
				stateMap.put(stateName, apiMap);
			}
			
		}
		return stateMap;
	}
	
	
}
