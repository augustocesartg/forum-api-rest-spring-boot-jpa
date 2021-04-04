package com.augustogiacomini.forum.repository;

import com.augustogiacomini.forum.modelo.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deveriaCarregarUmCursoAoBuscarPeloNome() {
        String nomeCurso = "HTML 5";

        Curso html5 = new Curso();
        html5.setNome(nomeCurso);
        html5.setCategoria("Programacao");
        entityManager.persist(html5);

        Curso curso = repository.findByNomeIgnoreCase(nomeCurso);

        assertNotNull(curso);
        assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    void naoDeveriaCarregarUmCursoCujoNomeNaoEstejaCadastrado() {
        String nomeCurso = "JPA";
        Curso curso = repository.findByNomeIgnoreCase(nomeCurso);

        assertNull(curso);
    }
}
