package com.fiap.techChallenge.infra.inbound.controller;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.infra.inbound.dto.OrderDto;
import com.fiap.techChallenge.infra.inbound.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto insertOrder(@RequestBody OrderDto orderDto){ return orderService.insert(orderDto);}

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> findAll(){
        return orderService.findAll();
    }
}
