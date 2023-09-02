package com.fiap.techChallenge.application.core.useCases;

import com.fiap.techChallenge.application.services.OrderService;
import com.fiap.techChallenge.application.services.PaymentService;
import com.fiap.techChallenge.application.useCases.OrderUseCases;
import com.fiap.techChallenge.domain.Order;
import com.fiap.techChallenge.presentation.dtos.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderUseCasesTest {

    @Mock
    private OrderService orderService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private OrderUseCases orderUseCases;

    private Order ORDER = buildOrder();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidOrder_WhenSave_thenSaveAndReturnOrder() {
        when(orderService.insert(ORDER)).thenReturn(ORDER);
        when(paymentService.MakePayment(any())).thenReturn(true);

        var saveOrder = orderUseCases.insert(OrderDto.of(ORDER));
        assertNotNull(saveOrder);
        verify(orderService, times(1)).insert(ORDER);
    }

    private Order buildOrder() {
        return Order.builder()
                .id(2L)
                .customerId(1L)
                .price(new BigDecimal(20))
                .paymentMethod("CARTAO")
                .client("Rodrigo")
                .build();
    }
}
