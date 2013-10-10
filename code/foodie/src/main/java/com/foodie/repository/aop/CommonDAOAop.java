package com.foodie.repository.aop;

import com.foodie.repository.BaseDAO;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonDAOAop {
    @Pointcut("@annotation(com.foodie.repository.annotation.JdoOperation)")
    public void point(){};
    
    @Before(value = "point()")
    public void beforeMethod(JoinPoint j) {
        BaseDAO dao = (BaseDAO)j.getTarget();
        dao.openPersistenceManager();
        
    };
    @After(value = "point()")
    @AfterThrowing(value = "point()")
    public void afterMethod(JoinPoint j) {
        BaseDAO dao = (BaseDAO)j.getTarget();
        dao.closePersistenceManager();
    }

}
