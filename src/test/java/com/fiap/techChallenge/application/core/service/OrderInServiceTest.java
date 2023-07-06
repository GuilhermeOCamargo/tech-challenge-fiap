package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.ports.out.OrderOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    private Order buildOrder(){
        return Order.builder()
                .customerId(1L)
                .price(new BigDecimal(20))
                .paymentMethod("CARTAO")
                .client("Rodrigo")
                .build();
    }
}
