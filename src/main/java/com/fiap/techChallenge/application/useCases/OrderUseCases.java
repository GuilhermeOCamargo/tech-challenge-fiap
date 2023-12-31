package com.fiap.techChallenge.application.useCases;

import com.fiap.techChallenge.application.services.OrderService;
import com.fiap.techChallenge.application.services.PaymentService;
import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.domain.Status;
import com.fiap.techChallenge.domain.exceptions.*;
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

    public OrderDto updateStatus(Long id, String newStatus) {
        try {
            Order order = orderService.updateStatus(id, new Status(newStatus));
            return OrderDto.of(order);
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new DataInputException(e.getMessage());
        }
    }

    public OrderDto findById(Long id){
        try {
            Order order = orderService.findById(id);
            return OrderDto.of(order);
        }catch (NotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }
}
