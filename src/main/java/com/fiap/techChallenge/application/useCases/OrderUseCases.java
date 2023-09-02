package com.fiap.techChallenge.application.useCases;

import com.fiap.techChallenge.application.services.OrderService;
import com.fiap.techChallenge.application.services.PaymentService;
import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.domain.exceptions.NotFoundException;
import com.fiap.techChallenge.domain.exceptions.PaymentException;
import com.fiap.techChallenge.domain.exceptions.PaymentNotAuthorizedException;
import com.fiap.techChallenge.domain.exceptions.ResourceNotFoundException;
import com.fiap.techChallenge.presentation.dtos.OrderDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderUseCases {

    private final OrderService orderService;
    private final PaymentService paymentService;

    public OrderDto insert(@Valid OrderDto orderdto) throws PaymentNotAuthorizedException {
        try{

            if(!paymentService.MakePayment(orderdto.toDomain()))
                throw new PaymentNotAuthorizedException("Pagamento não autorizado");

            var order = orderService.insert(orderdto.toDomain());
            return orderdto.of(order);
        }catch (PaymentNotAuthorizedException e) {
            throw new PaymentException(e.getMessage());
        }
    }

    public List<OrderDto> findAll(){
        try {
            List<Order> orders = orderService.findAll();
            return orders.stream().map(x -> OrderDto.of(x)).collect(Collectors.toList());
        }catch (NotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }
}
