package com.foodie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodie.config.ApplicationHelper;
import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.foodie.model.People;
import com.foodie.model.Restaurant;
import com.foodie.service.DataStoreSetup;
import com.foodie.service.NameListService;
import com.foodie.service.PublicAccessService;
import com.foodie.service.Session;
import com.foodie.service.SessionService;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
@RequestMapping(value=ApplicationHelper.cst_API_path, 
	produces = "application/json")
public class ApiController {
	
	@Autowired
	NameListService nameService;
	
	@Autowired
	PublicAccessService publicAccessService;
	
	@Autowired
	SessionService sessionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.cst_API_getNameList,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<People> getNameList() {	
		try
		{
			List<People> result = nameService.getAllNames();
			return (QueryResult<People>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<People>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.cst_API_getRestaurantById,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<Restaurant> getRestaurantById(@RequestParam("restaurantId") String restaurantId) {	
		try
		{
			Restaurant result = publicAccessService.getRestaurantById(
					KeyFactory.stringToKey(restaurantId));
			return (QueryResult<Restaurant>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<Restaurant>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.cst_API_getMenu,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<Menu> getMenu(@RequestParam("restaurantId") String restaurantId) {	
		try
		{
			List<Menu> result = publicAccessService.getMenu(
					KeyFactory.stringToKey(restaurantId));
			return (QueryResult<Menu>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<Menu>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.cst_API_getMenuItem,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<MenuItem> getMenuItem(@RequestParam("menuId") String menuId) {	
		try
		{
			List<MenuItem> result = publicAccessService.getMenuItem(
					KeyFactory.stringToKey(menuId));
			return (QueryResult<MenuItem>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<MenuItem>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.cst_API_getMenuById,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<Menu> getMenuById(@RequestParam("menuId") String menuId) {	
		try
		{
			Menu result =  publicAccessService.getMenuById(KeyFactory.stringToKey(menuId));
			return (QueryResult<Menu>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<Menu>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.cst_API_getMenuItemById,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<MenuItem> getMenuItemById(@RequestParam("menuItemId") String menuItemId) {	
		try
		{
			MenuItem result =  publicAccessService.getMenuItemById(KeyFactory.stringToKey(menuItemId));
			return (QueryResult<MenuItem>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<MenuItem>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@RequestMapping(value=ApplicationHelper.cst_API_openSession,
		method = RequestMethod.GET)
	@ResponseBody
    public void openSession(HttpServletResponse resp) throws IOException {	
		Cookie testCookie = new Cookie(ApplicationHelper.cst_testCookieName, 
				ApplicationHelper.cst_testCookieValue);
		resp.addCookie(testCookie);
		resp.sendRedirect(ApplicationHelper.cst_API_path + 
				ApplicationHelper.cst_API_generateSessionId);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.cst_API_generateSessionId,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<String> generateSessionId(HttpServletRequest req, 
    		HttpServletResponse resp) {	
		QueryResult<String> result = null;
		
		//At moment there is no exception thrown 
		try{
			Boolean cookieSupport = sessionService.checkCookieSupport(req);
			String sessionId = sessionService.generateSessionId(req, resp,cookieSupport);
			
			result = (QueryResult<String>) QueryResult.CreateFromSuccess(sessionId);
			result.setMessage(ApplicationHelper.cst_cookieSupport +
					cookieSupport);	

		}catch(Exception e){
			result =  (QueryResult<String>) QueryResult.CreateFromFailure(e.toString());
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/setMockNames",method = RequestMethod.GET)
	@ResponseBody
	public Result setMockNames(){
		try{			
			DataStoreSetup.setup();
			return Result.CreateFromSuccess(true);
		}catch(Exception e){
			return Result.CreateFromFailure(e);
		}
		
		
	}
}
