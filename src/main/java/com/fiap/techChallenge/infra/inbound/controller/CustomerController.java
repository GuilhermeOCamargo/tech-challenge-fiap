package com.fiap.techChallenge.infra.inbound.controller;

import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;
import com.fiap.techChallenge.infra.inbound.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto insertUser(@RequestBody CustomerDto userDto) {
        return customerService.insert(userDto);
    }

    @GetMapping("cpf/{cpf}")
    public CustomerDto findUserByCpf(@PathVariable("cpf") String cpf) {
        return customerService.findByCpf(cpf);
    }

}
