package com.fiap.techChallenge.infra.inbound.controller;

import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;
import com.fiap.techChallenge.infra.inbound.dto.ErrorResponseDto;
import com.fiap.techChallenge.infra.inbound.service.CustomerService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Tag(name = "Clientes", description = "Operações para gerenciamento de clientes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Cadastra cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class))
                    }),
            @ApiResponse(responseCode = "409", description = "Cliente já cadastrado",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            ),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para o cadastro do cliente",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto insertUser(@RequestBody CustomerDto userDto) {
        return customerService.insert(userDto);
    }

    @Operation(summary = "Buscar cliente por cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            ),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para o consulta do cliente",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            )
    })
    @GetMapping("cpf/{cpf}")
    public CustomerDto findUserByCpf(@PathVariable("cpf") String cpf) {
        return customerService.findByCpf(cpf);
    }

}

