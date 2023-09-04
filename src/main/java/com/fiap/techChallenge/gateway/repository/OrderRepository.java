package com.fiap.techChallenge.gateway.repository;

import com.fiap.techChallenge.gateway.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT p FROM OrderEntity p WHERE p.status <> 'finalizado'")
    List<OrderEntity> findOrders();
}
