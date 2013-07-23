package com.foodie.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Service;

import com.foodie.model.People;
import com.foodie.repository.PMF;

@Service
public class DataStoreSetup {

	public static void  setup(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<People> result = new ArrayList<People>();
		result.add(new People("Bin","Han"));
		result.add(new People("Yan","Li"));
		result.add(new People("Jun","Ze"));
		result.add(new People("Test1","Test2"));
		try{
			for(People p:result){
				pm.makePersistent(p);
			}
		}finally{
			pm.close();
		}
		
	}
		
}
