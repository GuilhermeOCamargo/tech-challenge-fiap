package com.fiap.techChallenge.application.ports.out;

import com.fiap.techChallenge.application.core.domain.Order;

public interface PaymentOutPort {
    boolean MakePayment(Order order);
}
