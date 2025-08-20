package com.example.forum.controller;

import com.example.forum.domain.model.Topico;
import com.example.forum.domain.model.dto.DadosAtualizacaoDTO;
import com.example.forum.domain.model.dto.DadosCadastroDTO;
import com.example.forum.domain.model.dto.DadosListagemDTO;
import com.example.forum.domain.model.dto.DadosRespostaDTO;
import com.example.forum.domain.repository.CursoRepository;
import com.example.forum.domain.repository.TopicoRepository;
import com.example.forum.domain.repository.UsuarioRepository;
import com.example.forum.domain.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/topicos")
public class ForumController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;
    @PostMapping
    @Transactional
    public ResponseEntity<DadosRespostaDTO> cadastrar(@RequestBody @Valid DadosCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        var autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado!"));
        var curso = cursoRepository.findById(dados.idCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        var topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        var dto = new DadosRespostaDTO(topico);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemDTO>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {

        var page = topicoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosRespostaDTO> detalhar(@PathVariable Long id) {
        var topicoDTO = topicoService.detalhar(id);
        return ResponseEntity.ok(topicoDTO);
    }

    // UPDATE
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosRespostaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoDTO dados) {
        var topicoAtualizadoDTO = topicoService.atualizar(id, dados);
        return ResponseEntity.ok(topicoAtualizadoDTO);
    }

    // DELETE (exclusão lógica)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        topicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
