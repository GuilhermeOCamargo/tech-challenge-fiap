package com.fiap.techChallenge.infrastructure.product;

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
}
