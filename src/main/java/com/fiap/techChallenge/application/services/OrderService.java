package com.fiap.techChallenge.application.services;


import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.domain.Status;

import java.util.List;

public interface OrderService {
    Order insert (Order order);
    List<Order> findAll();
    Order findById(Long id);
    Order updateStatus(Long id, Status newStatus);
}
