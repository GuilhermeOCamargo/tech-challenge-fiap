package com.fiap.techChallenge.application.ports.out;

import com.fiap.techChallenge.application.core.domain.Order;

import java.util.List;

public interface OrderOutPort {
    Order save(Order order);
    List<Order> findAll();
}
