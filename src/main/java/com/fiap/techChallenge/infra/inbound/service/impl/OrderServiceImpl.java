package com.fiap.techChallenge.infra.inbound.service.impl;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.fiap.techChallenge.application.ports.in.OrderInPort;
import com.fiap.techChallenge.infra.inbound.dto.OrderDto;
import com.fiap.techChallenge.infra.inbound.exception.DataInputException;
import com.fiap.techChallenge.infra.inbound.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderInPort port;
    public OrderDto insert(@Valid OrderDto orderdto) {
        try{
            var order = port.insert(orderdto.toDomain());
            return orderdto.of(order);
        }catch (InvalidDataException e) {
            throw new DataInputException(e.getMessage());
        }
    }
}
