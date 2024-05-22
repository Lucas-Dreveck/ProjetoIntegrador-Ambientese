package com.ambientese.grupo5.Model;

import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "Resposta")
public class RespostaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "formulario_id")
    private FormularioModel formulario;

    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    private PerguntasModel pergunta;

    @Enumerated(EnumType.STRING)
    @Column(name = "resposta")
    private RespostasEnum resposta;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FormularioModel getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioModel formulario) {
        this.formulario = formulario;
    }

    public PerguntasModel getPergunta() {
        return pergunta;
    }

    public void setPergunta(PerguntasModel pergunta) {
        this.pergunta = pergunta;
    }

    public RespostasEnum getResposta() {
        return resposta;
    }

    public void setResposta(RespostasEnum resposta) {
        this.resposta = resposta;
    }
}
