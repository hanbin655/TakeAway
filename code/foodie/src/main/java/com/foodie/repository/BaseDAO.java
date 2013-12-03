package com.foodie.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jdo.PersistenceManager;

public class BaseDAO {
    @Autowired
    protected PersistenceManager pm;

    public void openPersistenceManager() {
//        pm =pmf.getPersistenceManager();
    }

    public void closePersistenceManager() {
//        if (pm != null && !pm.isClosed()) pm.close();
    }

}
