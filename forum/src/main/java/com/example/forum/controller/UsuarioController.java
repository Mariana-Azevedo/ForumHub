package com.example.forum.controller;

import com.example.forum.domain.model.dto.DadosRespostaUsuarioDTO;
import com.example.forum.domain.model.dto.DadosUsuarioDTO;
import com.example.forum.domain.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRespostaUsuarioDTO> cadastrar(@RequestBody @Valid DadosUsuarioDTO dados, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.criarUsuario(dados);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        var dto = new DadosRespostaUsuarioDTO(usuario);

        return ResponseEntity.created(uri).body(dto);
    }
}
