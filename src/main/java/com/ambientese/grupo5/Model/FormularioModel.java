package com.ambientese.grupo5.Model;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Formulario")
public class FormularioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formulario_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "certificado_id")
    private CertificadoModel certificado;

    @OneToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaModel empresa;

    @NotNull
    private Date dataRespostas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CertificadoModel getCertificado() {
        return certificado;
    }

    public void setCertificado(CertificadoModel certificado) {
        this.certificado = certificado;
    }

    public EmpresaModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa) {
        this.empresa = empresa;
    }

    public @NotNull Date getDataRespostas() {
        return dataRespostas;
    }

    public void setDataRespostas(Date dataRespostas) {
        this.dataRespostas = dataRespostas;
    }

    @Transient
    private List<PerguntasModel> perguntas;
    @Transient
    private List<RespostaUsuarioModel> respostas;

    public List<PerguntasModel> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntasModel> perguntas) {
        this.perguntas = perguntas;
    }

    public List<RespostaUsuarioModel> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaUsuarioModel> respostas) {
        this.respostas = respostas;
    }
}
