package com.foodie;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jdo.PersistenceManager;


public class BaseSpringDAOTest extends BaseSpringTest {
    @Autowired
    protected PersistenceManager pm;

    public void init() {
        super.init();
    }

    @Test
    public void dummy() {

    }

    public void dispose() {
        super.dispose();
    }


}
