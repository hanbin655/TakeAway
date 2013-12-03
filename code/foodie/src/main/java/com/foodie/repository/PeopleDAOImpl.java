package com.foodie.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.stereotype.Repository;

import com.foodie.model.People;
import com.google.appengine.api.datastore.KeyFactory;

@Repository
public class PeopleDAOImpl implements PeopleDAO {

    private PersistenceManagerFactory pmf;
    private static final String cst_getAllQuery = "select from " + People.class.getName();

    public PeopleDAOImpl() {
        pmf = PMF.get();
    }

    @Override
    public void add(People p) {
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            pm.makePersistent(p);
        } finally {
            pm.close();
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<People> getAllPeople() {
        List<People> querryResult = null;
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            querryResult = (List<People>) pm.newQuery(cst_getAllQuery).execute();
        } finally {
            pm.close();
        }
        return querryResult;
    }

    @Override
    public People getByKey(String key) {
        People p = null;
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            p = (People) pm.getObjectById(People.class, KeyFactory.stringToKey(key));
        } finally {
            pm.close();
        }
        return p;
    }

}
