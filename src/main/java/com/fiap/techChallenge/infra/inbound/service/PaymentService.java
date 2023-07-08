package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.application.core.domain.Order;

public interface PaymentService {
    boolean MakePayment(Order order);
}
