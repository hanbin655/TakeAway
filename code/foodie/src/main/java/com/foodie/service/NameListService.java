package com.foodie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodie.model.People;
import com.foodie.repository.PeopleDAO;
import com.foodie.repository.PeopleDAOImpl;


@Service
public class NameListService {
	private PeopleDAO pdao = new PeopleDAOImpl();
	
	public List<People> getAllNames(){
		
		List<People> result = pdao.getAllPeople();
		return result;
	}
	
}
