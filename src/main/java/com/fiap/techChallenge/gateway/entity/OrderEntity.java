package com.fiap.techChallenge.gateway.entity;

import com.fiap.techChallenge.domain.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String client;
    private BigDecimal price;
    private String status;
    private String paymentMethod;
    @OneToMany(mappedBy = "orders", orphanRemoval = true, cascade = CascadeType.ALL)

    private List<OrderItemsEntity> orderItems;

    public static OrderEntity of(Order order) {
        var orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        orderEntity.setStatus(order.status().value());
        orderEntity.setOrderItems(order.orderItems().stream().map(item -> OrderItemsEntity.of(item, orderEntity)).collect(Collectors.toList()));
        return orderEntity;
    }

    public Order toDomain() {
        return Order.builder()
                .id(this.id)
                .customerId(this.customerId)
                .client(this.client)
                .price(this.price)
                .status(this.status)
                .paymentMethod(this.paymentMethod)
                .orderItems(this.orderItems.stream().map(OrderItemsEntity::converter).collect(Collectors.toList()))
                .build();
    }
}
