package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.infra.inbound.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto insert(OrderDto orderdto);
    List<OrderDto> findAll();
    OrderDto findById(Long id);
}
