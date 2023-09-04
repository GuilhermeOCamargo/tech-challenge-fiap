package com.fiap.techChallenge.infra.inbound.service.impl;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.core.exceptions.PaymentNotAuthorizedException;
import com.fiap.techChallenge.application.ports.in.OrderInPort;
import com.fiap.techChallenge.application.ports.in.PaymentInPort;
import com.fiap.techChallenge.infra.inbound.dto.OrderDto;
import com.fiap.techChallenge.infra.inbound.exception.DataInputException;
import com.fiap.techChallenge.infra.inbound.exception.PaymentException;
import com.fiap.techChallenge.infra.inbound.exception.ResourceNotFoundException;
import com.fiap.techChallenge.infra.inbound.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderInPort port;
    private final PaymentInPort paymentInPort;
    public OrderDto insert(@Valid OrderDto orderdto) throws PaymentNotAuthorizedException {
        try{

            if(!paymentInPort.MakePayment(orderdto.toDomain()))
                throw new PaymentNotAuthorizedException("Payment not authorized");

            var order = port.insert(orderdto.toDomain());
            return orderdto.of(order);
        }catch (PaymentNotAuthorizedException e) {
            throw new PaymentException(e.getMessage());
        }
    }

    public List<OrderDto> findAll(){
        try {
            List<Order> orders = port.findAll();

            var finalList = orders.stream().map(x -> OrderDto.of(x)).collect(Collectors.toList());

            return  finalList;

        }catch (NotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    public OrderDto findById(Long id){
        try {
            Order order = port.findById(id);
            return OrderDto.of(order);
        }catch (NotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }
}