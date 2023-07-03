package com.fiap.techChallenge.infra.outbound.repository.mariadb;

import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, String> {
}
