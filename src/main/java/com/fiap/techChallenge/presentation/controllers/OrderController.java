package com.fiap.techChallenge.presentation.controllers;

import com.fiap.techChallenge.application.useCases.OrderUseCases;
import com.fiap.techChallenge.presentation.dtos.ErrorResponseDto;
import com.fiap.techChallenge.presentation.dtos.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "Pedidos", description = "Operações para gerenciamento de pedidos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderUseCases orderUseCases;

    @Operation(summary = "Efetuar pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido crido", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))
            }),
            @ApiResponse(responseCode = "402", description = "Erro ao processar pagamento", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto insertOrder(@RequestBody OrderDto orderDto) {
        return orderUseCases.insert(orderDto);
    }

    @Operation(summary = "Consultar pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> findAll() {
        return orderUseCases.findAll();
    }

    @Operation(summary = "Atualizar status da ordem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema( implementation = OrderDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            }),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para a atualização do status", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @PutMapping("/{id}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable(name = "id") Long id,
            @PathVariable(name = "status") String newStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(orderUseCases.updateStatus(id, newStatus));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto findById(@Valid @PathVariable Long id){
        return orderUseCases.findById(id);
    }
}
