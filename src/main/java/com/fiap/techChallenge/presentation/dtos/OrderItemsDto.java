package com.fiap.techChallenge.presentation.dtos;

import com.fiap.techChallenge.domain.OrderItems;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemsDto {

    public Long id;
    public Long orderId;
    public Long productId;
    public String description;
    public Long quantity;

    public OrderItems toDomain(){
        return OrderItems.builder()
                .orderId(this.orderId)
                .productId(this.productId)
                .description(this.description)
                .quantity(this.quantity)
                .build();
    }
}
