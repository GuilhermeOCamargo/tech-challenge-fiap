package com.fiap.techChallenge.infra.outbound.repository.mariadb;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
