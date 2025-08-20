package com.example.forum.domain.model.dto;

import com.example.forum.domain.model.Usuario;

public record DadosRespostaUsuarioDTO (
        Long id,
        String nome,
        String email
) {
    public DadosRespostaUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}

