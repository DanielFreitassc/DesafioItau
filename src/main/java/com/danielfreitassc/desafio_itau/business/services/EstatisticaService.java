package com.danielfreitassc.desafio_itau.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.danielfreitassc.desafio_itau.controllers.dtos.EstatisticasResponseDTO;
import com.danielfreitassc.desafio_itau.controllers.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstatisticaService {
    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatiscasTransacoes(Integer intervaloDeBusca) {
        log.info("Iniciada busca de estatíscas de transações pelo período de tempo: " + intervaloDeBusca);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloDeBusca);

        if(transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
                
        log.info("Estatisticas retornadas com sucesso");

        return new EstatisticasResponseDTO(
            estatisticasTransacoes.getCount(),
            estatisticasTransacoes.getSum(),
            estatisticasTransacoes.getAverage(),
            estatisticasTransacoes.getMin(),
            estatisticasTransacoes.getMax()
        );
    }
}
