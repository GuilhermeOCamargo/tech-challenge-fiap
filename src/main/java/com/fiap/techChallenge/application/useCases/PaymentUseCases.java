package com.fiap.techChallenge.application.useCases;

import com.fiap.techChallenge.application.services.OrderService;
import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.domain.Status;
import com.fiap.techChallenge.domain.exceptions.DataInputException;
import com.fiap.techChallenge.domain.exceptions.InvalidDataException;
import com.fiap.techChallenge.domain.exceptions.NotFoundException;
import com.fiap.techChallenge.domain.exceptions.ResourceNotFoundException;
import com.fiap.techChallenge.application.enums.PaymentStatusEnum;
import com.fiap.techChallenge.presentation.dtos.OrderDto;
import com.fiap.techChallenge.presentation.dtos.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentUseCases {

    @Autowired
    private final OrderService orderService;

    public boolean makePayment(Order order) {
        return true;
    }

    public OrderDto receivePayment(PaymentDto paymentDto) {
        try {
            PaymentStatusEnum paymentStatus = PaymentStatusEnum.valueOfIgnoreCase(paymentDto.getPaymentStatus());
            Order order = orderService.updateStatus(paymentDto.getOrderId(), new Status(paymentStatus.getStatus()));
            return OrderDto.of(order);
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new DataInputException(e.getMessage());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DataInputException(e.getMessage());
        }
    }
}
