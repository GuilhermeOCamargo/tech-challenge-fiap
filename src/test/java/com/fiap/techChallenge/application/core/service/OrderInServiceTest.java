package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.services.OrderService;
import com.fiap.techChallenge.application.useCases.OrderUseCases;
import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.domain.exceptions.InvalidDataException;
import com.fiap.techChallenge.domain.exceptions.NotFoundException;
import com.fiap.techChallenge.presentation.dtos.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderInServiceTest {

    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderUseCases orderUseCases;

    private Order ORDER = buildOrder();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidOrder_WhenSave_thenSaveAndReturnOrder(){
        when(orderService.insert(ORDER)).thenReturn(ORDER);

        var saveOrder = orderUseCases.insert(OrderDto.of(ORDER));
        assertNotNull(saveOrder);
        verify(orderService, times(1)).insert(ORDER);
    }

    @Test
    public void givenInvalidStatus_WhenInsert_thenThrowInvalidData() {
        Order invalidStatusOrder = buildOrder("recebido");

        var receivedException = assertThrowsExactly(InvalidDataException.class,
                () -> orderUseCases.insert(OrderDto.of(invalidStatusOrder)),
                "Exception different than expected!");

        verify(orderService, never()).insert(any());
        assertEquals("Invalid Status to insert order",
                receivedException.getMessage(),
                "Exception message different than expected!");
    }

    @Test
    public void givenInvalidStatus_WhenUpdateStatus_thenThrowInvalidData() {
        Order iniciedOrder = buildOrder("iniciado");
        Long testId = 1l;
        String invalidStatus = "recebido";

        when(orderService.findById(testId)).thenReturn(iniciedOrder);

        var receivedException = assertThrowsExactly(InvalidDataException.class,
                () -> orderUseCases.updateStatus(testId, invalidStatus),
                "Exception different than expected!");

        verify(orderService, never()).updateStatus(any(), any());
        assertEquals("Status " + iniciedOrder.status().value() + " cannot be changed to " + invalidStatus,
                receivedException.getMessage(),
                "Exception message different than expected!");
    }

    @Test
    public void givenOrderIdNotRegistered_WhenUpdateStatus_thenThrowNotFound() {
        Long testId = 1l;
        String status = "recebido";

        when(orderService.findById(testId)).thenReturn(null);

        var receivedException = assertThrowsExactly(NotFoundException.class,
                () -> orderUseCases.updateStatus(testId, status),
                "Exception different than expected!");

        verify(orderService, never()).updateStatus(any(), any());
        assertEquals("Ordem não encontrada",
                receivedException.getMessage(),
                "Exception message different than expected!");
    }

    private Order buildOrder() {
        return Order.builder()
                .customerId(1L)
                .price(new BigDecimal(20))
                .status("iniciado")
                .paymentMethod("CARTAO")
                .client("Rodrigo")
                .orderItems(new ArrayList<>())
                .build();
    }

    private Order buildOrder(String status) {
        return Order.builder()
                .customerId(1L)
                .price(new BigDecimal(20))
                .status(status)
                .paymentMethod("CARTAO")
                .client("Rodrigo")
                .orderItems(new ArrayList<>())
                .build();
    }
}
