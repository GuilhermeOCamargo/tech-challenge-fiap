package com.fiap.techChallenge.application.useCases;

import com.fiap.techChallenge.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentUseCases {

    public boolean MakePayment(Order order){
        return true;
    }
}
