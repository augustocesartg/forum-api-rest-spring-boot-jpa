package com.augustogiacomini.forum.controller.form;

import com.augustogiacomini.forum.modelo.Curso;
import com.augustogiacomini.forum.repository.CursoRepository;
import com.augustogiacomini.forum.modelo.Topico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    @NotNull @NotEmpty
    private String nomeCurso;

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNomeIgnoreCase(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
