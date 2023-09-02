package com.fiap.techChallenge.application.services.impl;

import com.fiap.techChallenge.application.services.OrderService;
import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.gateway.entity.OrderEntity;
import com.fiap.techChallenge.gateway.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public Order insert(Order order) {
        return orderRepository.save(OrderEntity.of(order)).toDomain();
    }

    @Override
    public List<Order> findAll() {
        var orderList = orderRepository.findAll();
        return orderList.stream().map(order -> order.toDomain()).collect(Collectors.toList());
    }
}
