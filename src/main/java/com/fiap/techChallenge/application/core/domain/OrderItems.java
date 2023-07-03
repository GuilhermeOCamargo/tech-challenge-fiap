package com.fiap.techChallenge.application.core.domain;

import lombok.Builder;

@Builder
public record OrderItems(Long id, Long orderId, Long productId, String description, Long quantity) {

}
