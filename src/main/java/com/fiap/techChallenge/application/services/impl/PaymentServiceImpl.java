package com.fiap.techChallenge.application.services.impl;

import com.fiap.techChallenge.application.services.PaymentService;
import com.fiap.techChallenge.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    public boolean MakePayment(Order order) {
        return true;
    }

}
