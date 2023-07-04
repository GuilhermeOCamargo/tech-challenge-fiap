package com.fiap.techChallenge.infra.outbound.repository.mariadb;

import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
