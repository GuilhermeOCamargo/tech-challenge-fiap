package com.fiap.techChallenge.infra.outbound.repository.mariadb.entity;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.domain.OrderItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Double price;
    private String status;
    private String paymentMethod;
    @OneToMany(mappedBy = "orders", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderItemsEntity> orderItems;

    public OrderEntity(Order order) {
        this(order.id(), order.customerId(), order.price(), order.status(), order.paymentMethod(), null);
    }

    public List<OrderItems> getOrderItems() {

    }

    public Order toDomain(){
        return Order.builder()
                .id(this.id)
                .customerId(this.customerId)
                .price(this.price)
                .status(this.status)
                .paymentMethod(this.paymentMethod)
                .orderItems(getOrderItems())
                .build();
    }


}
