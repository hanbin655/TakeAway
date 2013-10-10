package com.foodie;

import com.foodie.repository.PMF;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;

import javax.jdo.PersistenceManager;

public class BaseTest {
    protected final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    protected PersistenceManager pmf; 
    @Before
    public void setUp() {
        helper.setUp();
        pmf = PMF.get().getPersistenceManager();
    }
    @After
    public void tearDown() {
        pmf.close();
        helper.tearDown();
    }

}
