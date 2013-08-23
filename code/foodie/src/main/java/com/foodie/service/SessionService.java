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
			Cookie sessionCookie = new Cookie(ApplicationHelper.cst_SessionCookie,
					sessionId);
			resp.addCookie(sessionCookie);
		}
		
		return sessionId;
	}
	
	public Boolean checkCookieSupport(HttpServletRequest req){
		Boolean cookieSupport = false;
		Cookie[] cookies = req.getCookies();
		
		try{
			for(Cookie c :cookies){
				if(c.getName().equals(ApplicationHelper.cst_testCookieName)
						&& c.getValue().equals(ApplicationHelper.cst_testCookieValue)){
					cookieSupport = true;
					break;
				}
			}
			
		}catch(Exception e){
			cookieSupport = false;
		}
		return cookieSupport;
		
	}
	
}
 