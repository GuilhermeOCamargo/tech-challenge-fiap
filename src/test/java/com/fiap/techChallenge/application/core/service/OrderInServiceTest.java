package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.core.domain.Status;
import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.out.OrderOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderInServiceTest {

    @Mock
    private OrderOutPort orderOutPort;
    @InjectMocks
    private OrderInService orderInService;

    private Order ORDER = buildOrder();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidOrder_WhenSave_thenSaveAndReturnOrder(){
        when(orderOutPort.save(ORDER)).thenReturn(ORDER);

        var saveOrder = orderInService.insert(ORDER);
        assertNotNull(saveOrder);
        verify(orderOutPort, times(1)).save(ORDER);
    }

    @Test
    public void givenInvalidStatus_WhenInsert_thenThrowInvalidData() {
        Order invalidStatusOrder = buildOrder("recebido");

        var receivedException = assertThrowsExactly(InvalidDataException.class,
                () -> orderInService.insert(invalidStatusOrder),
                "Exception different than expected!");

        verify(orderOutPort, never()).save(any());
        assertEquals("Invalid Status to insert order",
                receivedException.getMessage(),
                "Exception message different than expected!");
    }

    @Test
    public void givenInvalidStatus_WhenUpdateStatus_thenThrowInvalidData() {
        Order iniciedOrder = buildOrder("iniciado");
        Long testId = 1l;
        Status invalidStatus = new Status("recebido");

        when(orderOutPort.findById(testId)).thenReturn(iniciedOrder);

        var receivedException = assertThrowsExactly(InvalidDataException.class,
                () -> orderInService.updateStatus(testId, invalidStatus),
                "Exception different than expected!");

        verify(orderOutPort, never()).update(any());
        assertEquals("Status " + iniciedOrder.status().value() + " cannot be changed to " + invalidStatus.value(),
                receivedException.getMessage(),
                "Exception message different than expected!");
    }

    @Test
    public void givenOrderIdNotRegistered_WhenUpdateStatus_thenThrowNotFound() {
        Long testId = 1l;
        Status status = new Status("recebido");

        when(orderOutPort.findById(testId)).thenReturn(null);

        var receivedException = assertThrowsExactly(NotFoundException.class,
                () -> orderInService.updateStatus(testId, status),
                "Exception different than expected!");

        verify(orderOutPort, never()).update(any());
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
