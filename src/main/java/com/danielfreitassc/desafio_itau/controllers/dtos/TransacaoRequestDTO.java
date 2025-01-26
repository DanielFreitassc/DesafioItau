package com.danielfreitassc.desafio_itau.controllers.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(
    double valor,
    OffsetDateTime dataHora
) {
    
}
