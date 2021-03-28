package com.augustogiacomini.forum.repository;

import com.augustogiacomini.forum.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoNomeIgnoreCase(String nomeCurso, Pageable paginacao);
}
