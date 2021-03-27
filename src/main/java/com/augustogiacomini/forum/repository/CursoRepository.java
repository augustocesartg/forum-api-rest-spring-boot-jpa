package com.augustogiacomini.forum.repository;

import com.augustogiacomini.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNomeIgnoreCase(String nome);
}
