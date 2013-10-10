package com.foodie.repository;

import com.foodie.model.Order;
import com.foodie.repository.annotation.JdoOperation;
import com.google.appengine.api.datastore.Key;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends BaseDAO implements OrderDAO {

    @JdoOperation
    @Override
    public void persist(Order order) {
        pm.makePersistent(order);
    }

    @JdoOperation
    @Override
    public Order getOrderById(Key orderId) {
        return pm.getObjectById(Order.class, orderId);
    }

}
