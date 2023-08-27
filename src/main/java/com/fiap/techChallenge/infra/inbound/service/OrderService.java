package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.infra.inbound.dto.OrderDto;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface OrderService {
    OrderDto insert(OrderDto orderdto);
    List<OrderDto> findAll();
    OrderDto updateStatus(Long id, String newStatus);
}
