package com.fiap.techChallenge.domain;

import com.fiap.techChallenge.domain.exceptions.InvalidDataException;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.google.common.base.Strings;

import static com.fiap.techChallenge.util.ConstantsUtil.INVALID_ORDER;


public record Order(Long id, Long customerId, String client, BigDecimal price, Status status, String paymentMethod,
                    List<OrderItems> orderItems) {

    public Order{
        if(Objects.isNull(customerId))
            throw new InvalidDataException(INVALID_ORDER + ": customerId must not be null");
        
        if(Strings.isNullOrEmpty(client))
            throw new InvalidDataException(INVALID_ORDER + ": client must not be null or empty");

        if(Objects.isNull(price))
            throw new InvalidDataException(INVALID_ORDER + ": price must not be null");

        if(Strings.isNullOrEmpty(paymentMethod))
            throw new InvalidDataException(INVALID_ORDER + ": paymentMethod must not be null or empty");
        
        if(Objects.isNull(orderItems))
            throw new InvalidDataException(INVALID_ORDER + ": orderItems must not be null");

    }

    @Builder
    public Order(Long id, Long customerId, String client, BigDecimal price, String status, String paymentMethod,
            List<OrderItems> orderItems) {
        this(id, customerId, client, price, new Status(status), paymentMethod, orderItems);
    }
}
