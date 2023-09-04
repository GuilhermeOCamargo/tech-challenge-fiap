package com.fiap.techChallenge.application.services.impl;

import com.fiap.techChallenge.application.enums.StatusEnum;
import com.fiap.techChallenge.application.services.OrderService;
import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.domain.Status;
import com.fiap.techChallenge.domain.exceptions.InvalidDataException;
import com.fiap.techChallenge.domain.exceptions.NotFoundException;
import com.fiap.techChallenge.gateway.entity.OrderEntity;
import com.fiap.techChallenge.gateway.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fiap.techChallenge.util.ConstantsUtil.ORDER_NOT_FOUND;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public Order insert(Order order) {
        if (!order.status().value().equalsIgnoreCase(StatusEnum.INICIADO.name())) {
            throw new InvalidDataException("Invalid Status to insert order");
        }
        return orderRepository.save(OrderEntity.of(order)).toDomain();
    }

    @Override
    public List<Order> findAll() {
        var orderList = orderRepository.findAll();
        return orderList.stream().map(order -> order.toDomain()).collect(Collectors.toList());
    }

    @Override
    public Order updateStatus(Long id, Status newStatus) {
        Order orderSaved = findById(id);
        if (Objects.isNull(orderSaved)) {
            throw new NotFoundException(ORDER_NOT_FOUND);
        }

        if (!orderSaved.status().newStatusIsValid(newStatus)) {
            throw new InvalidDataException(
                    "Status " + orderSaved.status().value() + " cannot be changed to " + newStatus.value());
        }

        Order orderToBeSaved = new Order(orderSaved.id(),
                orderSaved.customerId(),
                orderSaved.client(),
                orderSaved.price(),
                newStatus,
                orderSaved.paymentMethod(),
                orderSaved.orderItems());

        return orderRepository.save(OrderEntity.of(orderToBeSaved)).toDomain();
    }

    @Override
    public Order findById(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        Order order = orderEntity.isPresent() ? orderEntity.get().toDomain() : null;
        if (Objects.isNull(order)) {
            throw new NotFoundException(ORDER_NOT_FOUND);
        }
        return order;
    }
}
