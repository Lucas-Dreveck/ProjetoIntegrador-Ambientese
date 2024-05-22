package com.ambientese.grupo5.Model;

import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Formulario")
public class FormularioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL)
    private List<PerguntasModel> perguntas;

    @Column(name = "certificado")
    @Enumerated(EnumType.STRING)
    private NivelCertificadoEnum certificado;

    @OneToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaModel empresa;

    @ElementCollection(targetClass = RespostasEnum.class)
    @CollectionTable(name = "respostas", joinColumns = @JoinColumn(name = "formulario_id"))
    @Column(name = "resposta")
    @Enumerated(EnumType.STRING)
    private List<RespostasEnum> respostas;

    @Column(name = "pontuacao_final")
    private Integer pontuacaoFinal;

    @Column(name = "pontuacao_social")
    private Integer pontuacaoSocial;

    @Column(name = "pontuacao_ambiental")
    private Integer pontuacaoAmbiental;

    @Column(name = "pontuacao_governamental")
    private Integer pontuacaoGovernamental;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_respostas")
    private Date dataRespostas;

    // Getters and Setters

    public Integer getPontuacaoFinal() {
        return pontuacaoFinal;
    }

    public void setPontuacaoFinal(Integer pontuacaoFinal) {
        this.pontuacaoFinal = pontuacaoFinal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PerguntasModel> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntasModel> perguntas) {
        this.perguntas = perguntas;
    }

    public NivelCertificadoEnum getCertificado() {
        return certificado;
    }

    public void setCertificado(NivelCertificadoEnum certificado) {
        this.certificado = certificado;
    }

    public EmpresaModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa) {
        this.empresa = empresa;
    }

    public List<RespostasEnum> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostasEnum> respostas) {
        this.respostas = respostas;
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
