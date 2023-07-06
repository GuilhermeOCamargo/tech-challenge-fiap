package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.ports.in.OrderInPort;
import com.fiap.techChallenge.application.ports.out.OrderOutPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderInService implements OrderInPort {

    private final OrderOutPort orderOutPort;

    @Override
    public Order insert(Order order) {
        return orderOutPort.save(order);
    }

    @Override
    public List<Order> findAll(){
        return orderOutPort.findAll();
    }
}
