package com.fiap.techChallenge.infrastructure.product;

import com.fiap.techChallenge.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private double price;

    private String description;

    @ElementCollection
    private List<String> images;

    public static ProductEntity createEntity(Product product) {
        return ProductEntity.builder()
                .price(product.getPrice())
                .category(product.getCategory())
                .description(product.getDescription())
                .images(product.getImages())
                .build();
    }
}
