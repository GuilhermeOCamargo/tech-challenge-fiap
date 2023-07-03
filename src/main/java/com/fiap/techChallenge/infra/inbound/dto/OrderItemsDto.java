package com.fiap.techChallenge.infra.inbound.dto;

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

}
