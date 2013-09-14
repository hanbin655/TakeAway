package com.foodie.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.foodie.model.session.SessionState;

public class SessionStateTest {

	@Test
	public void stateTransitTest() throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException{
		SessionState state = new SessionState("OrderSpecified");
		String url = "setAddressById";
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		String sessionIdLabel = "sessionId";
		String sessionIdValue = "afasdfadf";
		paramMap.put(sessionIdLabel,sessionIdValue);
		String addressIdLabel = "addressId";
		String addressIdValue = "afasdfadf";
		paramMap.put(addressIdLabel, addressIdValue);
		Boolean sucessFlag = state.transfer(url, paramMap);
		assertEquals(sucessFlag,true);
		assertEquals("AddressSpecified",state.getState());
	}
}
