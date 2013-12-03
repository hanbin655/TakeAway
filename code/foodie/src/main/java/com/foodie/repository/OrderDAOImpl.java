package com.foodie.repository;

import com.foodie.model.Order;
import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends BaseDAO implements OrderDAO {

    @Override
    public void persist(Order order) {
        pm.makePersistent(order);
    }

    @Override
    public Order getOrderById(Key orderId) {
        return pm.getObjectById(Order.class, orderId);
    }

}
