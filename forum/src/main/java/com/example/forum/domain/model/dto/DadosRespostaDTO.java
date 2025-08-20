package com.example.forum.domain.model.dto;

import com.example.forum.domain.model.EstadoEnum;
import com.example.forum.domain.model.Topico;

import java.time.LocalDateTime;

public record DadosRespostaDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        EstadoEnum status,
        String nomeAutor,
        String nomeCurso
) {
    public DadosRespostaDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}
