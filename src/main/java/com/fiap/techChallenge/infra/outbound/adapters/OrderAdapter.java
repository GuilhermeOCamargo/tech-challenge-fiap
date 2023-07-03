package com.fiap.techChallenge.infra.outbound.adapters;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.ports.out.OrderOutPort;
import com.fiap.techChallenge.infra.outbound.repository.mariadb.OrderRepository;
import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderAdapter implements OrderOutPort {

    private final OrderRepository orderRepository;
    @Override
    public Order save(Order order) {
        return orderRepository.save(new OrderEntity(order)).toDomain();

    }

    @Override
    public List<Order> findAll() {
        var orderList = orderRepository.findAll();
        return orderList.size() > 0 ?
    }
}
