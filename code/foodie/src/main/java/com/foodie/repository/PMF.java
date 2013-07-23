package com.foodie.repository;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.stereotype.Repository;

@Repository
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}