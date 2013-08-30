package com.foodie.yan;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class MainTest {

	@Test
	public void stateTransitTest() throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException{
		State state = new State("OrderSpecified");
		String url = "setAddressById";
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		String sessionIdLabel = "sessionId";
		String sessionIdValue = "afasdfadf";
		paramMap.put(sessionIdLabel,sessionIdValue);
		String addressIdLabel = "addressId";
		String addressIdValue = "afasdfadf";
		paramMap.put(addressIdLabel, addressIdValue);
		state.transfer(url, paramMap);
		assertEquals(state.getState(),"AddressSpecified");
	}
}
