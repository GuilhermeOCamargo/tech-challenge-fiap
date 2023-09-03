package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.domain.Status;

import java.util.List;

public interface OrderInPort {
    Order insert (Order order);
    List<Order> findAll();
    Order findById(Long id);
    Order updateStatus(Long id, Status newStatus);
}
