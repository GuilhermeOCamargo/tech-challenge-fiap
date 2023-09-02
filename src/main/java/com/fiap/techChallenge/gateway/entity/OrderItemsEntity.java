package com.fiap.techChallenge.gateway.entity;

import com.fiap.techChallenge.domain.OrderItems;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderitems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemsEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String description;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity orders;

    public static OrderItems converter(OrderItemsEntity entity){
        return OrderItems.builder()
                .id(entity.getId())
                .orderId(entity.getOrders().getId())
                .description(entity.getDescription())
                .quantity(entity.getQuantity())
                .productId(entity.getProductId())
                .build();
    }
    public static OrderItemsEntity of(OrderItems orderItems, OrderEntity orderEntity){
        return OrderItemsEntity.builder()
                .orders(orderEntity)
                .productId(orderItems.productId())
                .description(orderItems.description())
                .quantity(orderItems.quantity())
                .build();
    }

    public OrderItems toDomain(){
        return OrderItems.builder()
                .id(this.id)
                .productId(this.productId)
                .description(this.description)
                .quantity(this.quantity)
                .build();
    }


}
