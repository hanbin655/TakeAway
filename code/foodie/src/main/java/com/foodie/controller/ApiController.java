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
import com.foodie.service.SessionService;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
@RequestMapping(value=ApplicationHelper.CST_API_PATH, 
	produces = "application/json")
public class ApiController {
	
	@Autowired
	NameListService nameService;
	
	@Autowired
	PublicAccessService publicAccessService;
	
	@Autowired
	SessionService sessionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.CST_API_GET_NAME_LIST,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<People> getNameList() {
		try
		{
			List<People> result = nameService.getAllNames();
			return (QueryResult<People>) QueryResult.createFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<People>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.CST_API_GET_RESTAURANT_BY_ID,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<Restaurant> getRestaurantById(@RequestParam("restaurantId") String restaurantId) {	
		try
		{
			Restaurant result = publicAccessService.getRestaurantById(
					KeyFactory.stringToKey(restaurantId));
			return (QueryResult<Restaurant>) QueryResult.createFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<Restaurant>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.CST_API_GET_MENU,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<Menu> getMenu(@RequestParam("restaurantId") String restaurantId) {	
		try
		{
			List<Menu> result = publicAccessService.getMenu(
					KeyFactory.stringToKey(restaurantId));
			return (QueryResult<Menu>) QueryResult.createFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<Menu>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.CST_API_GET_MENU_ITEM,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<MenuItem> getMenuItem(@RequestParam("menuId") String menuId) {	
		try
		{
			List<MenuItem> result = publicAccessService.getMenuItem(
					KeyFactory.stringToKey(menuId));
			return (QueryResult<MenuItem>) QueryResult.createFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<MenuItem>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.CST_API_GET_MENU_BY_ID,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<Menu> getMenuById(@RequestParam("menuId") String menuId) {	
		try
		{
			Menu result =  publicAccessService.getMenuById(KeyFactory.stringToKey(menuId));
			return (QueryResult<Menu>) QueryResult.createFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<Menu>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.CST_API_GET_MENU_ITEM_BY_ID,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<MenuItem> getMenuItemById(@RequestParam("menuItemId") String menuItemId) {	
		try
		{
			MenuItem result =  publicAccessService.getMenuItemById(KeyFactory.stringToKey(menuItemId));
			return (QueryResult<MenuItem>) QueryResult.createFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<MenuItem>) QueryResult.CreateFromFailure(e.getMessage());
		}
		
	}
	
	@RequestMapping(value=ApplicationHelper.CST_API_OPEN_SESSION,
		method = RequestMethod.GET)
	@ResponseBody
    public void openSession(HttpServletResponse resp) throws IOException {	
		Cookie testCookie = new Cookie(ApplicationHelper.CST_TEST_COOKIE_NAME, 
				ApplicationHelper.CST_TEST_COOKIE_VALUE);
		resp.addCookie(testCookie);
		resp.sendRedirect(ApplicationHelper.CST_API_PATH + 
				ApplicationHelper.CST_API_GENERATE_SESSION_ID);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value=ApplicationHelper.CST_API_GENERATE_SESSION_ID,
		method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<String> generateSessionId(HttpServletRequest req, 
    		HttpServletResponse resp) {	
		QueryResult<String> result = null;
		
		//At moment there is no exception thrown 
		try{
			Boolean cookieSupport = sessionService.checkCookieSupport(req);
			String sessionId = sessionService.generateSessionId(req, resp,cookieSupport);
			
			result = (QueryResult<String>) QueryResult.createFromSuccess(sessionId);
			result.setMessage(ApplicationHelper.CST_COOKIE_SUPPORT +
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
