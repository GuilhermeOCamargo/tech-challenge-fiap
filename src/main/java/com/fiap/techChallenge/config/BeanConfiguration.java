package com.fiap.techChallenge.config;

import com.fiap.techChallenge.application.core.service.CustomerInService;
import com.fiap.techChallenge.application.core.service.OrderInService;
import com.fiap.techChallenge.application.core.service.PaymentInService;
import com.fiap.techChallenge.infra.outbound.adapters.CustomerAdapter;
import com.fiap.techChallenge.infra.outbound.adapters.OrderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerInService saveUserService(CustomerAdapter customerAdapter) {
        return new CustomerInService(customerAdapter);
    }
    @Bean
    public OrderInService saveOrderService(OrderAdapter orderAdapter){
        return new OrderInService(orderAdapter);
    }
    @Bean
    public PaymentInService pagamentoService(){
        return new PaymentInService();
    }
}
