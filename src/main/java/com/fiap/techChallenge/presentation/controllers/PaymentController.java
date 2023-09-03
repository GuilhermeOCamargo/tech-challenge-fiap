package com.fiap.techChallenge.presentation.controllers;

import com.fiap.techChallenge.application.useCases.PaymentUseCases;
import com.fiap.techChallenge.presentation.dtos.ErrorResponseDto;
import com.fiap.techChallenge.presentation.dtos.OrderDto;
import com.fiap.techChallenge.presentation.dtos.PaymentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payment")
@Tag(name = "Pagamentos", description = "Operações para gerenciamento de pagamentos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {

    private final PaymentUseCases paymentUseCases;


    @Operation(summary = "Receber o pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status de pagamento do pedido atualizado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            }),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para a atualização do status de pagamento do pedido", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @PostMapping
    public ResponseEntity<OrderDto> receivePayment(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentUseCases.receivePayment(paymentDto));
    }
}
