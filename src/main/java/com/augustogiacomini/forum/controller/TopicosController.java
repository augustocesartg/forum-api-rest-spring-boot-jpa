package com.augustogiacomini.forum.controller;

import com.augustogiacomini.forum.controller.dto.TopicoDto;
import com.augustogiacomini.forum.controller.form.AtualizacaoTopicoForm;
import com.augustogiacomini.forum.controller.form.TopicoForm;
import com.augustogiacomini.forum.controller.dto.DetalhesDoTopicoDto;
import com.augustogiacomini.forum.modelo.Topico;
import com.augustogiacomini.forum.repository.CursoRepository;
import com.augustogiacomini.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        List<Topico> topicos = nomeCurso == null ?
                topicoRepository.findAll() : topicoRepository.findByCursoNomeIgnoreCase(nomeCurso);

        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);

        topicoRepository.save(topico);

        URI uri =  uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (!topico.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = form.atualizar(optional.get(), topicoRepository);

        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
