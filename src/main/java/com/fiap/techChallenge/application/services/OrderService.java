package com.fiap.techChallenge.application.services;


import com.fiap.techChallenge.domain.Order;

import java.util.List;

public interface OrderService {
    Order insert(Order order);
    List<Order> findAll();
}
