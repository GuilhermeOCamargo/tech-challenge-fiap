package com.fiap.techChallenge.infra.inbound.service;

import java.util.List;

import com.fiap.techChallenge.infra.inbound.dto.OrderDto;

public interface OrderService {
    OrderDto insert(OrderDto orderdto);
    List<OrderDto> findAll();
    OrderDto updateStatus(Long id, String newStatus);
}
