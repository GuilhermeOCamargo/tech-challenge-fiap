package com.fiap.techChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fiap.techChallenge.infrastructure.exceptions")
@ComponentScan(basePackages = {"com.fiap.techChallenge.infrastructure.exceptions","com.fiap.techChallenge.application" })

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
