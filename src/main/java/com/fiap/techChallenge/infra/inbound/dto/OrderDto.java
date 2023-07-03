package com.fiap.techChallenge.infra.inbound.dto;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.domain.OrderItems;
import com.sun.jna.WString;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {

    private long orderId;
    private long customerId;
    private double price;
    private String status;
    private String paymentMethod;
    private List<OrderItems> orderItems;

    public Order toDomain(){
        return Order.builder()
                .id(this.getOrderId())
                .customerId(this.getCustomerId())
                .price(this.getPrice())
                .status(this.getStatus())
                .paymentMethod(this.getPaymentMethod())
                .orderItems(this.getOrderItems())
                .build();
    }

    public OrderDto of(Order order){
        return OrderDto.builder()
                .orderId(order.id())
                .customerId(order.customerId())
                .price(order.price())
                .status(order.status())
                .paymentMethod(order.paymentMethod())
                .orderItems(order.orderItems())
                .build();
    }
}
