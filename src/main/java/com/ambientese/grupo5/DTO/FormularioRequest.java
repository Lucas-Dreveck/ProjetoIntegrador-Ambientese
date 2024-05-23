package com.ambientese.grupo5.DTO;


import com.ambientese.grupo5.Model.Enums.RespostasEnum;

public class FormularioRequest {
    private Long NumeroPergunta;
    private String perguntaDescricao;
    private RespostasEnum respostaUsuario;

    public Long getNumeroPergunta() {
        return NumeroPergunta;
    }

    public void setNumeroPergunta(Long numeroPergunta) {
        this.NumeroPergunta = numeroPergunta;
    }

    public String getPerguntaDescricao() {
        return perguntaDescricao;
    }

    public void setPerguntaDescricao(String perguntaDescricao) {
        this.perguntaDescricao = perguntaDescricao;
    }

    public RespostasEnum getRespostaUsuario() {
        return respostaUsuario;
    }

    public void setRespostaUsuario(RespostasEnum respostaUsuario) {
        this.respostaUsuario = respostaUsuario;
    }
}
