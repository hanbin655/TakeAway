package com.foodie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.foodie.model.People;
import com.foodie.model.Restaurant;
import com.foodie.service.DataStoreSetup;
import com.foodie.service.NameListService;
import com.foodie.service.PublicAccessService;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
@RequestMapping(value="/api", produces = "application/json")
public class ApiController {
	
	@Autowired
	NameListService nameService;
	
	@Autowired
	PublicAccessService publicAccessService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getNameList",method = RequestMethod.GET)
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
	@RequestMapping(value="/getRestaurantById",method = RequestMethod.GET)
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
	@RequestMapping(value="/getMenu",method = RequestMethod.GET)
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
	@RequestMapping(value="/getMenuItem",method = RequestMethod.GET)
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
	@RequestMapping(value="/getMenuById",method = RequestMethod.GET)
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
	@RequestMapping(value="/getMenuItemById",method = RequestMethod.GET)
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
