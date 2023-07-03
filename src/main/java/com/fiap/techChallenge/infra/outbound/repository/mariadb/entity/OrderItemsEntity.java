package com.fiap.techChallenge.infra.outbound.repository.mariadb.entity;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.domain.OrderItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "orderitems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderItemsEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long productId;
    private String description;
    private Long quantity;
    @ManyToOne
    private OrderEntity orders;

    public OrderItemsEntity(OrderItems items){
        this(items.id(), items.orderId(), items.productId(), items.description(), items.quantity() );
    }

    public OrderItemsEntity(Long id, Long aLong, Long aLong1, String description, Long quantity) {
    }

    public OrderItems toDomain(){
        return OrderItems.builder()
                .id(this.id)
                .orderId(this.orderId)
                .productId(this.productId)
                .description(this.description)
                .quantity(this.quantity)
                .build();
    }
}
