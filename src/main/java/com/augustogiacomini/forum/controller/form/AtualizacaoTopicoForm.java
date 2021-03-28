package com.augustogiacomini.forum.controller.form;

import com.augustogiacomini.forum.modelo.Topico;
import com.augustogiacomini.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoTopicoForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    public Topico atualizar(Topico topico, TopicoRepository topicoRepository) {
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.getMensagem());

        return topico;
    }
}
