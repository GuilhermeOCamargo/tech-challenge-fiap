package com.fiap.techChallenge.application.core.service;

import static com.fiap.techChallenge.application.core.util.ConstantsUtil.ORDER_NOT_FOUND;

import java.util.List;
import java.util.Objects;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.domain.Status;
import com.fiap.techChallenge.application.core.enums.StatusEnum;
import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.in.OrderInPort;
import com.fiap.techChallenge.application.ports.out.OrderOutPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderInService implements OrderInPort {

    private final OrderOutPort orderOutPort;

    @Override
    public Order insert(Order order) {
        if (!order.status().value().equalsIgnoreCase(StatusEnum.INICIADO.name())) {
            throw new InvalidDataException("Invalid Status to insert order");
        }
        return orderOutPort.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderOutPort.findAll();
    }

    @Override
    public Order updateStatus(Long id, Status newStatus) {
        Order orderSaved = orderOutPort.findById(id);
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

        return orderOutPort.update(orderToBeSaved);
    }

    @Override
    public Order findById(Long id) {
        Order order = orderOutPort.findById(id);
        if (Objects.isNull(order)) {
            throw new NotFoundException(ORDER_NOT_FOUND);
        }
        return order;
    }
}
