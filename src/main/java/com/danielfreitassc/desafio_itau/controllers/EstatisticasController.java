package com.danielfreitassc.desafio_itau.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.desafio_itau.business.services.EstatisticaService;
import com.danielfreitassc.desafio_itau.controllers.dtos.EstatisticasResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class EstatisticasController {
    private final EstatisticaService estatisticaService;
    
    
    @GetMapping
    @Operation(description= "Endpoint responsavel por buscar estatísticas de transações")
    @ApiResponses(value= {
        @ApiResponse(responseCode= "200", description="Busca efetuada com sucesso"),
        @ApiResponse(responseCode="400", description="Erro na busca de estatísticas de transações"),
        @ApiResponse(responseCode="500", description="Erro interno do servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
                
            return ResponseEntity.ok(estatisticaService.calcularEstatiscasTransacoes(intervaloBusca));
        }
}
