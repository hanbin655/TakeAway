package com.foodie;
import com.foodie.repository.PMF;

import javax.jdo.PersistenceManager;

import org.junit.Test;


public class BaseSpringDAOTest extends BaseSpringTest {
    protected PersistenceManager pm;
    
    public void init() {
        super.init();
        pm = PMF.get().getPersistenceManager();
    }
    
    @Test
    public void dummy(){
    	
    }
    
    public void dispose() {
        if (pm != null && !pm.isClosed()) pm.close();
        super.dispose();
    }
    

}
