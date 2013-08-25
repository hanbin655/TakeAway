package com.foodie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodie.config.ApplicationHelper;
import com.foodie.model.People;
import com.foodie.repository.PeopleDAO;
import com.foodie.repository.PeopleDAOImpl;


@Service
public class NameListService extends AbstractService {
	private PeopleDAO pdao = new PeopleDAOImpl();

	
	public List<People> getAllNames() throws NullPointerException{
		
		List<People> result = pdao.getAllPeople();
		if(result.isEmpty())
		{
			this.ThrowException(new NullPointerException(
					ApplicationHelper.NULL_POINTER_EXCEPTION_MSG));
		}
		return result;
	}


	
	
}
