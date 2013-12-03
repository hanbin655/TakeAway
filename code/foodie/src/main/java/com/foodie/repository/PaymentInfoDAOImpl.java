package com.foodie.repository;

import com.foodie.model.PaymentInfo;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentInfoDAOImpl extends BaseDAO implements PaymentInfoDAO {

    @Override
    public void persist(PaymentInfo paymentInfo) {
        pm.makePersistent(paymentInfo);
    }

}
