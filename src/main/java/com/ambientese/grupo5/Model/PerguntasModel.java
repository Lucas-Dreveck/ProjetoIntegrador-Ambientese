package com.ambientese.grupo5.Model;


import com.ambientese.grupo5.Model.Enums.EixoEnum;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "Perguntas")
public class PerguntasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perguntas_id")
    private long id;

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    @NotBlank
    private EixoEnum perguntasEixo;

    @ManyToOne
    @JoinColumn(name = "formulario_id")
    private FormularioModel formulario;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EixoEnum getPerguntasEixo() {
        return perguntasEixo;
    }

    public void setPerguntasEixo(EixoEnum perguntasEixo) {
        this.perguntasEixo = perguntasEixo;
    }

    public FormularioModel getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioModel formulario) {
        this.formulario = formulario;
    }
}
