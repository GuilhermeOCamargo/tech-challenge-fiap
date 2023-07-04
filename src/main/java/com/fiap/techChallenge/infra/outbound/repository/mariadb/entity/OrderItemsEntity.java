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

    private Long productId;
    private String description;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity orders;

    public OrderItemsEntity(OrderItems items){
        this(items.id(), items.productId(), items.description(), items.quantity(), null );
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
