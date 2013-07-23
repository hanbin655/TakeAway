package com.foodie.repository;

import java.util.List;

import com.foodie.model.People;
import com.google.appengine.api.datastore.Key;

public interface PeopleDAO {
	
	public void add(People p);
	public List<People> getAllPeople();
	public People getByKey(Key key);

}
