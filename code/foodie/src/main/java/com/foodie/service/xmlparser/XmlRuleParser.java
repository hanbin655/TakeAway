package com.foodie.service.xmlparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

import com.foodie.model.session.APIRule;

public class XmlRuleParser {

	private static String xmlLocation = "src/main/resources/session/stateTransition.xml";
	private HashMap<String,HashMap<String,APIRule>> stateMap;
	private static XmlRuleParser xmlruleParser = null;
	private boolean isLoaded = false;;
	public boolean isLoaded(){
		return isLoaded;
	}
	private XmlRuleParser() {
		
		stateMap = new HashMap<String,HashMap<String, APIRule>>();
		try{
			readRuleFromXml();
			isLoaded = true;
		}catch(Exception e){
			isLoaded = false;
		}
		
	}
	
	private void readRuleFromXml() throws ParserConfigurationException, 
	SAXException, IOException{
		File fXmlFile = new File(xmlLocation);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
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
	public static XmlRuleParser getInstance() throws 
	ParserConfigurationException, SAXException, IOException{
		if(xmlruleParser == null){
			xmlruleParser = new XmlRuleParser();
		}
		return xmlruleParser;
	}
	
	public APIRule getAPIRule(String stateName, String url){
		APIRule apiRule= null;
		if(isLoaded){
			HashMap<String,APIRule> apiMap = stateMap.get(stateName);
			if(apiMap != null){
				apiRule = apiMap.get(url);
			}
		}
		
		return apiRule;
	}
	
}
