package com.danielfreitassc.desafio_itau.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.desafio_itau.business.services.TransacaoService;
import com.danielfreitassc.desafio_itau.controllers.dtos.TransacaoRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description= "Endpoint responsavel por adicioanr transações")
    @ApiResponses(value= {
        @ApiResponse(responseCode= "201", description="Transação gravada com sucesso"),
        @ApiResponse(responseCode="422", description="Campos não atendem os requisitos da transação"),
        @ApiResponse(responseCode="400", description="Erro de requisição"),
        @ApiResponse(responseCode="500", description="Erro interno do servidor")
    })

    public ResponseEntity<Void> fazerTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.fazerTransacao(transacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    
    @DeleteMapping
    @Operation(description= "Endpoint responsavel por deletar transações")
    @ApiResponses(value= {
        @ApiResponse(responseCode= "200", description="Transações deletadas com sucesso"),
        @ApiResponse(responseCode="400", description="Erro de requisição"),
        @ApiResponse(responseCode="500", description="Erro interno do servidor")
    })
    public ResponseEntity<Void> limparTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
