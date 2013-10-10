package com.foodie.repository;

import com.foodie.model.PaymentInfo;
import com.foodie.repository.annotation.JdoOperation;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentInfoDAOImpl extends BaseDAO implements PaymentInfoDAO {

    @JdoOperation
    @Override
    public void persist(PaymentInfo paymentInfo) {
        pm.makePersistent(paymentInfo);
    }

}
