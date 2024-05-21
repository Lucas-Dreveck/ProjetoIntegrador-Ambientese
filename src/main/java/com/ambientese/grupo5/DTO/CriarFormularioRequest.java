package com.ambientese.grupo5.DTO;

import com.ambientese.grupo5.Model.Enums.EixoEnum;

import java.util.List;

public class CriarFormularioRequest {
    private List<EixoEnum> eixos;
    private int perguntasPorEixo;

    public List<EixoEnum> getEixos() {
        return eixos;
    }

    public void setEixos(List<EixoEnum> eixos) {
        this.eixos = eixos;
    }

    public int getPerguntasPorEixo() {
        return perguntasPorEixo;
    }

    public void setPerguntasPorEixo(int perguntasPorEixo) {
        this.perguntasPorEixo = perguntasPorEixo;
    }
}
