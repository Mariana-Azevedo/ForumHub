package com.example.forum.domain.service;

import com.example.forum.domain.model.Usuario;
import com.example.forum.domain.model.dto.DadosUsuarioDTO;
import com.example.forum.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(DadosUsuarioDTO dados) {
        if (usuarioRepository.findByEmail(dados.email()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        var senhaHasheada = passwordEncoder.encode(dados.senha());

        var novoUsuario = new Usuario(null, dados.nome(), dados.email(), senhaHasheada);

        return usuarioRepository.save(novoUsuario);
    }
}
