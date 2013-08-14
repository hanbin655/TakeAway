package com.foodie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodie.model.People;
import com.foodie.repository.MenuDAO;
import com.foodie.repository.MenuDAOImpl;
import com.foodie.service.DataStoreSetup;
import com.foodie.service.NameListService;

@Controller
@RequestMapping(value="/api", produces = "application/json")
public class ApiController {
	
	@Autowired
	NameListService nameService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getNameList",method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<People> getNameList() {	
		//DataStoreSetup.setup();		
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
	
	//TODO: to be finished
	@RequestMapping(value="/getMenuList",method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<People> getMenuList() {	
		//DataStoreSetup.setup();		
		try
		{
			MenuDAO menuDAO = new MenuDAOImpl();
			List<People> result = menuDAO.get;
			return (QueryResult<People>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e)
		{
			return (QueryResult<People>) QueryResult.CreateFromFailure(e.getMessage());
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
