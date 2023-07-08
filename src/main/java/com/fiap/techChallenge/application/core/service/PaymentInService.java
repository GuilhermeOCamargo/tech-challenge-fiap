package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.exceptions.PaymentNotAuthorizedException;
import com.fiap.techChallenge.application.ports.in.PaymentInPort;

public class PaymentInService implements PaymentInPort {
    @Override
    public boolean MakePayment(Order order){
        return true;
    }
}
