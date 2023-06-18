package com.fiap.techChallenge.infrastructure.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.category = :category")
    List<ProductEntity> findByCategory(String category);
}
