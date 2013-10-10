package com.foodie.repository;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class BaseDAO {
    protected PersistenceManagerFactory pmf = PMF.get();
    protected PersistenceManager pm;
    
    public void openPersistenceManager() {
        pm =pmf.getPersistenceManager();
    }
    
    public void closePersistenceManager() {
        if (pm != null && !pm.isClosed()) pm.close();
    }

}
