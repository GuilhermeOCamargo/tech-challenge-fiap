package com.fiap.techChallenge.infra.inbound.service.impl;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.fiap.techChallenge.application.core.exceptions.PaymentNotAuthorizedException;
import com.fiap.techChallenge.application.ports.in.OrderInPort;
import com.fiap.techChallenge.application.ports.in.PaymentInPort;
import com.fiap.techChallenge.infra.inbound.dto.OrderDto;
import com.fiap.techChallenge.infra.inbound.exception.DataInputException;
import com.fiap.techChallenge.infra.inbound.exception.PaymentException;
import com.fiap.techChallenge.infra.inbound.exception.ResourceNotFoundException;
import com.fiap.techChallenge.infra.inbound.service.OrderService;
import com.fiap.techChallenge.infrastructure.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderInPort port;
    private final PaymentInPort pagamentoPort;
    public OrderDto insert(@Valid OrderDto orderdto) throws PaymentNotAuthorizedException {
        try{

            if(!pagamentoPort.MakePayment(orderdto.toDomain()))
                throw new PaymentNotAuthorizedException("Pagamento não autorizado");

            var order = port.insert(orderdto.toDomain());
            return orderdto.of(order);
        }catch (PaymentNotAuthorizedException e) {
            throw new PaymentException(e.getMessage());
        }
    }

    public List<OrderDto> findAll(){
        try {
            List<Order> orders = port.findAll();
            return orders.stream().map(x -> OrderDto.of(x)).collect(Collectors.toList());
        }catch (NotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }
}
