package com.fiap.techChallenge.application.core.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.google.common.base.Strings;

public record Order(Long id, Long customerId, String client, BigDecimal price, Status status, String paymentMethod,
        List<OrderItems> orderItems) {

    public Order{
        if(Objects.isNull(customerId))
            throw new InvalidDataException("Invalid Order");
        
        if(Strings.isNullOrEmpty(client))
            throw new InvalidDataException("Invalid Order");

        if(Objects.isNull(price))
            throw new InvalidDataException("Invalid Order");

        if(Strings.isNullOrEmpty(paymentMethod))
            throw new InvalidDataException("Invalid Order");
        
        if(Objects.isNull(orderItems))
            throw new InvalidDataException("Invalid Order");

    }

    @Builder
    public Order(Long id, Long customerId, String client, BigDecimal price, String status, String paymentMethod,
            List<OrderItems> orderItems) {
        this(id, customerId, client, price, new Status(status), paymentMethod, orderItems);
    }
}
