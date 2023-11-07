package com.fiap.techChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fiap.techChallenge.domain.exceptions","com.fiap.techChallenge.application", "com.fiap.techChallenge.presentation",
       "com.fiap.techChallenge.gateway.repository","com.fiap.techChallenge.configuration" })
public class Application {

    public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
    }

}