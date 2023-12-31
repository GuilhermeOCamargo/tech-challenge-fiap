package com.fiap.techChallenge.presentation.dtos;

import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.domain.OrderItems;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {

    private long orderId;
    private long customerId;
    private String client;
    private BigDecimal price;
    private String status;
    private String paymentMethod;
    private List<OrderItems> orderItems;

    public Order toDomain(){
        return Order.builder()
                .id(this.getOrderId())
                .customerId(this.getCustomerId())
                .client(this.getClient())
                .price(this.getPrice())
                .status(this.getStatus())
                .paymentMethod(this.getPaymentMethod())
                .orderItems(this.getOrderItems())
                .build();
    }

    public static OrderDto of(Order order) {
        return OrderDto.builder()
                .orderId(order.id())
                .customerId(order.customerId())
                .client(order.client())
                .price(order.price())
                .status(order.status().value())
                .paymentMethod(order.paymentMethod())
                .orderItems(order.orderItems())

                .build();
    }
}
