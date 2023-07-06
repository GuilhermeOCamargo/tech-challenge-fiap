package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.fiap.techChallenge.application.core.exceptions.OrderItemsNotFoundException;
import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.OrderItemsEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
public record Order(Long id, Long customerId, String client, BigDecimal price, String status, String paymentMethod, List<OrderItems> orderItems) { }

