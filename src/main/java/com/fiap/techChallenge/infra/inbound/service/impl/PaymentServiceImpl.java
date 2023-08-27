package com.fiap.techChallenge.infra.inbound.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.domain.Status;
import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.in.OrderInPort;
import com.fiap.techChallenge.infra.inbound.dto.OrderDto;
import com.fiap.techChallenge.infra.inbound.dto.PaymentDto;
import com.fiap.techChallenge.infra.inbound.enums.PaymentStatusEnum;
import com.fiap.techChallenge.infra.inbound.exception.DataInputException;
import com.fiap.techChallenge.infra.inbound.exception.ResourceNotFoundException;
import com.fiap.techChallenge.infra.inbound.service.PaymentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImpl implements PaymentService {

    private final OrderInPort orderPort;

    public boolean MakePayment(Order order) {
        return true;
    }

    @Override
    public OrderDto receivePayment(PaymentDto paymentDto) {
        try {
            PaymentStatusEnum paymentStatus = PaymentStatusEnum.valueOfIgnoreCase(paymentDto.getPaymentStatus());
            Order order = orderPort.updateStatus(paymentDto.getOrderId(), new Status(paymentStatus.getStatus()));
            return OrderDto.of(order);
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new DataInputException(e.getMessage());
        } catch (IllegalArgumentException | NullPointerException e){
            throw new DataInputException(e.getMessage());
        }
    }

}
