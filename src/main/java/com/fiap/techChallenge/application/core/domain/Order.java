package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import lombok.Builder;

import java.util.List;

@Builder
public record Order(Long id, Long customerId, Double price, String status, String paymentMethod, List<OrderItems> orderItems) {

    public Order{
        if(customerId.equals(0)){
            throw new InvalidDataException("Informar cliente");
        }
    }
}

