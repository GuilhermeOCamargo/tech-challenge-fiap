package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.application.ports.in.PagamentoInPort;

public class PagamentoInService implements PagamentoInPort {
    @Override
    public boolean RealizaPagamento(Order order) {
        return false;
    }
}
