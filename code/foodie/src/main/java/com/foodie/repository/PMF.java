package com.foodie.repository;

import org.springframework.stereotype.Repository;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

@Repository
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {
    }

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}