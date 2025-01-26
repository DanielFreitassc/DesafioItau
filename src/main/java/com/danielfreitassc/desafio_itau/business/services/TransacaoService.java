package com.danielfreitassc.desafio_itau.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.danielfreitassc.desafio_itau.controllers.dtos.TransacaoRequestDTO;
import com.danielfreitassc.desafio_itau.infrastructure.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();
    
    public void fazerTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        log.info("Iniciado o processamento de gravar transações" + transacaoRequestDTO);

        if(transacaoRequestDTO.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        } 
        
        if(transacaoRequestDTO.valor() < 0) {
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pdoe ser menor que 0");
        }

        listaTransacoes.add(transacaoRequestDTO);
        log.info("Transações adicionadas com sucesso");
    }

    public void limparTransacoes() {
        log.info("Inciado processamento para deletar transações");

        listaTransacoes.clear();
        log.info("Transações removidas com sucesso");

    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Inciada buscas de transações por tempo " + intervaloBusca);

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                    .isAfter(dataHoraIntervalo)).toList();
    }
}
