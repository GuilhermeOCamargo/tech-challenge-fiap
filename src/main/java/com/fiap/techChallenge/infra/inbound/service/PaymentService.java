package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.infra.inbound.dto.OrderDto;
import com.fiap.techChallenge.infra.inbound.dto.PaymentDto;

public interface PaymentService {
    boolean MakePayment(Order order);

    OrderDto receivePayment(PaymentDto paymentDto);
}
