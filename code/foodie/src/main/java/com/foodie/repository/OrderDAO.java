package com.foodie.repository;

import com.foodie.model.Order;
import com.google.appengine.api.datastore.Key;

public interface OrderDAO {
    public void persist(Order order);
    public Order getOrderById(Key orderId);

}
