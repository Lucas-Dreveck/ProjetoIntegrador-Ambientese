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

    private String Nivel;

    @NotNull
    @NotBlank
    private EixoEnum Eixo;

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

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public EixoEnum getEixo() {
        return Eixo;
    }

    public void setEixo(EixoEnum eixo) {
        Eixo = eixo;
    }
}
