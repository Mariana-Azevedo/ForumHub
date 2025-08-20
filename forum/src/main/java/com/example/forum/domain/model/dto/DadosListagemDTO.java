package com.example.forum.domain.model.dto;

import com.example.forum.domain.model.EstadoEnum;
import com.example.forum.domain.model.Topico;

import java.time.LocalDateTime;

public record DadosListagemDTO(
        Long id,
        String titulo,
        EstadoEnum status,
        LocalDateTime dataCriacao
) {
    public DadosListagemDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getStatus(), topico.getDataCriacao());
    }
}