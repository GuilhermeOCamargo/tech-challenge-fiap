package com.fiap.techChallenge.config;

import com.fiap.techChallenge.application.core.service.CustomerInService;
import com.fiap.techChallenge.application.core.service.ProductInService;
import com.fiap.techChallenge.application.ports.out.ProductOutPort;
import com.fiap.techChallenge.infra.outbound.adapters.CustomerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerInService customerInService(CustomerAdapter customerAdapter) {
        return new CustomerInService(customerAdapter);
    }

    @Bean
    public ProductInService productInService(ProductOutPort productOutPort) {
        return new ProductInService(productOutPort);
    }

}
