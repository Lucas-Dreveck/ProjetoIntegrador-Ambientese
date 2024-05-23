package com.ambientese.grupo5.DTO;

import java.util.Date;

import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;

public class FormularioRanking {
    private long id;
    private String empresaNome;
    private NivelCertificadoEnum nivelCertificado;
    private Integer pontuacaoFinal;
    private Integer pontuacaoSocial;
    private Integer pontuacaoAmbiental;
    private Integer pontuacaoGovernamental;
    private Date dataRespostas;

    public FormularioRanking(long id, String empresaNome, NivelCertificadoEnum nivelCertificado, Integer pontuacaoFinal, Integer pontuacaoSocial,
                                Integer pontuacaoAmbiental, Integer pontuacaoGovernamental, Date dataRespostas) {
        this.id = id;
        this.empresaNome = empresaNome;
        this.nivelCertificado = nivelCertificado;
        this.pontuacaoFinal = pontuacaoFinal;
        this.pontuacaoSocial = pontuacaoSocial;
        this.pontuacaoAmbiental = pontuacaoAmbiental;
        this.pontuacaoGovernamental = pontuacaoGovernamental;
        this.dataRespostas = dataRespostas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public NivelCertificadoEnum getNivelCertificado() {
        return nivelCertificado;
    }

    public void setNivelCertificado(NivelCertificadoEnum nivelCertificado) {
        this.nivelCertificado = nivelCertificado;
    }

    public Integer getPontuacaoFinal() {
        return pontuacaoFinal;
    }

    public void setPontuacaoFinal(Integer pontuacaoFinal) {
        this.pontuacaoFinal = pontuacaoFinal;
    }

    public Integer getPontuacaoSocial() {
        return pontuacaoSocial;
    }

    public void setPontuacaoSocial(Integer pontuacaoSocial) {
        this.pontuacaoSocial = pontuacaoSocial;
    }

    public Integer getPontuacaoAmbiental() {
        return pontuacaoAmbiental;
    }

    public void setPontuacaoAmbiental(Integer pontuacaoAmbiental) {
        this.pontuacaoAmbiental = pontuacaoAmbiental;
    }

    public Integer getPontuacaoGovernamental() {
        return pontuacaoGovernamental;
    }

    public void setPontuacaoGovernamental(Integer pontuacaoGovernamental) {
        this.pontuacaoGovernamental = pontuacaoGovernamental;
    }

    public Date getDataRespostas() {
        return dataRespostas;
    }

    public void setDataRespostas(Date dataRespostas) {
        this.dataRespostas = dataRespostas;
    }

    
}
