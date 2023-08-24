package com.fiap.techChallenge.infra.inbound.controller;

import com.fiap.techChallenge.application.core.domain.Order;
import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;
import com.fiap.techChallenge.infra.inbound.dto.ErrorResponseDto;
import com.fiap.techChallenge.infra.inbound.dto.OrderDto;
import com.fiap.techChallenge.infra.inbound.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "Pedidos", description = "Operações para gerenciamento de pedidos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Efetuar pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido crido",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))
                    }),
            @ApiResponse(responseCode = "402", description = "Erro ao processar pagamento",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto insertOrder(@RequestBody OrderDto orderDto){ return orderService.insert(orderDto);}

    @Operation(summary = "Consultar pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "20", description = "Pedidos encontrados",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            )
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> findAll(){
        return orderService.findAll();
    }
}
