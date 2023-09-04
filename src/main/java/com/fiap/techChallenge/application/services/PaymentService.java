package com.fiap.techChallenge.application.services;


import com.fiap.techChallenge.domain.Order;

public interface PaymentService {
    boolean MakePayment(Order order);
}
