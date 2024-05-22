package com.ambientese.grupo5.Model;

import com.ambientese.grupo5.Model.Enums.EixoEnum;
import jakarta.persistence.*;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Pergunta")
public class PerguntasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private EixoEnum perguntasEixo;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
    private List<RespostaModel> respostas;

    public PerguntasModel(String s, EixoEnum eixoEnum) {}

    public PerguntasModel() {}

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

    public List<RespostaModel> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaModel> respostas) {
        this.respostas = respostas;
    }
}
