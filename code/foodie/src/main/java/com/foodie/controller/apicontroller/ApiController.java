package com.foodie.controller.apicontroller;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodie.controller.QueryResult;
import com.foodie.facade.PeopleFacade;
import com.foodie.model.PMF;
import com.foodie.model.People;
import com.foodie.service.DataStoreSetup;

@Controller
@RequestMapping(value="/api", produces = "application/json")
public class ApiController {
	
	
	@RequestMapping(value="/getNameList",method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<PeopleFacade> getNameList() {	
		//DataStoreSetup.setup();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + People.class.getName();
		
		try{
			List<People> querryResult = (List<People>) pm.newQuery(query).execute();
			List<PeopleFacade> result = new ArrayList<PeopleFacade>();
			for(People p : querryResult){
				result.add(new PeopleFacade(p));
			}
			return (QueryResult<PeopleFacade>) QueryResult.CreateFromSuccess(result);
		}
		catch(Exception e){
			return (QueryResult<PeopleFacade>) QueryResult.CreateFromFailure();
		}finally{
			pm.close();
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
