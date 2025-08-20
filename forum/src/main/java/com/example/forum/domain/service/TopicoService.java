package com.example.forum.domain.service;

import com.example.forum.domain.model.dto.DadosAtualizacaoDTO;
import com.example.forum.domain.model.dto.DadosListagemDTO;
import com.example.forum.domain.model.dto.DadosRespostaDTO;
import com.example.forum.domain.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Page<DadosListagemDTO> listar(Pageable paginacao) {
        return topicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemDTO::new);
    }

    public DadosRespostaDTO detalhar(Long id) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com id: " + id));
        return new DadosRespostaDTO(topico);
    }

    @Transactional
    public DadosRespostaDTO atualizar(Long id, DadosAtualizacaoDTO dados) {
        var topico = topicoRepository.getReferenceById(id);
        topico.atualizarInformacoes(dados);

        return new DadosRespostaDTO(topico);
    }

    @Transactional
    public void excluir(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topico.excluir();
    }
}
