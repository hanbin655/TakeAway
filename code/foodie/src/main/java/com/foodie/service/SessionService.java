package com.foodie.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.foodie.config.ApplicationHelper;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class SessionService extends AbstractService {

	private static Map<String,Session> sessionList = new HashMap<String,Session>();
	private static long counter = 1;
	
	public Session createNewSession(){
		Session session = new Session();
		Key sessionId = KeyFactory.createKey(Session.class.getSimpleName(), counter++);
		session.setSessionId(sessionId);
		return session;
	}
	
	public Session createNewSession(Boolean cookieSupport){
		Session session = new Session(cookieSupport);
		Key sessionId = KeyFactory.createKey(Session.class.getSimpleName(), counter++);
		session.setSessionId(sessionId);
		sessionList.put(KeyFactory.keyToString(sessionId), session);
		return session;
	}
	
	public String generateSessionId(HttpServletRequest req, 
			HttpServletResponse resp, Boolean cookieSupport){
		Session session = createNewSession(cookieSupport);
		String sessionId = session.getSessionId();
		if(cookieSupport){
			Cookie sessionCookie = new Cookie(ApplicationHelper.CST_SESSION_COOKIE,
					sessionId);
			resp.addCookie(sessionCookie);
		}
		
		return sessionId;
	}
	
	public Boolean checkCookieSupport(HttpServletRequest req){
		Boolean cookieSupport = false;
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null){
			for(Cookie c :cookies){
				if(c.getName().equals(ApplicationHelper.CST_TEST_COOKIE_NAME)
						&& c.getValue().equals(ApplicationHelper.CST_TEST_COOKIE_VALUE)){
					cookieSupport = true;
					break;
				}
			}
		}
			
		return cookieSupport;
		
	}
	
}
 