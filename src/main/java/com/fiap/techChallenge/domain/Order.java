package com.fiap.techChallenge.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record Order(Long id, Long customerId, String client, BigDecimal price, String status, String paymentMethod, List<OrderItems> orderItems) { }

