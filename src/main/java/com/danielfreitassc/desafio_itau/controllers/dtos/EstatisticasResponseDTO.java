package com.danielfreitassc.desafio_itau.controllers.dtos;

public record EstatisticasResponseDTO(
    Long count,
    Double sum,
    Double avg,
    Double min,
    Double max
) {
    
}
