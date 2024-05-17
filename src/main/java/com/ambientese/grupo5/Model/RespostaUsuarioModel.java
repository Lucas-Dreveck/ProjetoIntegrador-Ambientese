package com.ambientese.grupo5.Model;

import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RespostaUsuario")
public class RespostaUsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private RespostasEnum Respostas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "formulario_id")
    private FormularioModel formulario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perguntas_id")
    private PerguntasModel perguntas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull @NotBlank RespostasEnum getRespostas() {
        return Respostas;
    }

    public void setRespostas(@NotNull @NotBlank RespostasEnum respostas) {
        Respostas = respostas;
    }

    public FormularioModel getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioModel formulario) {
        this.formulario = formulario;
    }

    public PerguntasModel getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(PerguntasModel perguntas) {
        this.perguntas = perguntas;
    }
}
