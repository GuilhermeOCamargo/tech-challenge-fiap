package com.fiap.techChallenge.application.ports.out;

import com.fiap.techChallenge.application.core.domain.Order;

public interface PagamentoOutPort {
    boolean RealizaPagamento(Order order);
}
