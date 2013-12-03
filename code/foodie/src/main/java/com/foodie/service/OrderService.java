package com.foodie.service;

import com.foodie.model.Order;
import com.foodie.model.PaymentInfo;
import com.foodie.model.session.Session;
import com.foodie.repository.OrderDAO;
import com.foodie.repository.PaymentInfoDAO;
import com.foodie.repository.SessionDAO;
import com.google.appengine.api.datastore.KeyFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    PaymentInfoDAO paymentInfoDAO;
    @Autowired
    SessionDAO sessionDAO;
    public String setOrder(String sessionId, Order order) {
        Session session = sessionDAO.getSessionById(KeyFactory.stringToKey(sessionId));
        order.setUserId(session.getUserId());
        orderDAO.persist(order);
        return KeyFactory.keyToString(order.getOrderId());
    }
    
    public String setPaymentInfo(String sessionId, PaymentInfo paymentInfo) {
        Session session = sessionDAO.getSessionById(KeyFactory.stringToKey(sessionId));
        Order order = orderDAO.getOrderById(KeyFactory.stringToKey(paymentInfo.getOrderId()));
        if (!order.getUserId().equals(session.getUserId())) return null;
        paymentInfoDAO.persist(paymentInfo);
        return KeyFactory.keyToString(paymentInfo.getPaymentInfoId());
    }
}
