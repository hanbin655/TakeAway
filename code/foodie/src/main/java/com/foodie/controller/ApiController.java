package com.foodie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodie.model.People;
import com.foodie.service.DataStoreSetup;
import com.foodie.service.NameListService;

@Controller
@RequestMapping(value="/api", produces = "application/json")
public class ApiController {
	
	@Autowired
	NameListService nameService;
	
	@RequestMapping(value="/getNameList",method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<People> getNameList() {	
		//DataStoreSetup.setup();
		List<People> result = nameService.getAllNames();
		if(result.isEmpty()){
			return (QueryResult<People>) QueryResult.CreateFromFailure();
		}else{
			return (QueryResult<People>) QueryResult.CreateFromSuccess(result);
		}
	}
	
	@RequestMapping(value="/setMockNames",method = RequestMethod.GET)
	@ResponseBody
	public String setMockNames(){
		try{
			DataStoreSetup.setup();
			return "Success";
		}catch(Exception e){
			return "Failure";
		}
		
		
	}
	
}
