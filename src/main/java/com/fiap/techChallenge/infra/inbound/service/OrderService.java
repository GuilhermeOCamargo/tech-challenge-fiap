package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.infra.inbound.dto.OrderDto;

public interface OrderService {
    OrderDto insert(OrderDto orderdto);
}
