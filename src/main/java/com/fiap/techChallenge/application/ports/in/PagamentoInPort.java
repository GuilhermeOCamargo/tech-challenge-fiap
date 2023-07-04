package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Order;

public interface PagamentoInPort {

    boolean RealizaPagamento(Order order);
}
