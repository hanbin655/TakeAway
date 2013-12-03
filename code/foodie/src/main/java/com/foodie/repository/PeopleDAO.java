package com.foodie.repository;

import java.util.List;

import com.foodie.model.People;

public interface PeopleDAO {
	
	public void add(People p);
	public List<People> getAllPeople();
	public People getByKey(String key);

}
