package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Order;

public interface PaymentInPort {

    boolean MakePayment(Order order);
}
