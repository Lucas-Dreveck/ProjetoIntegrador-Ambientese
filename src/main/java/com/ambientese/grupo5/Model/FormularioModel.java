package com.ambientese.grupo5.Model;

import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
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

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL)
    @JoinColumn (name = "perguntas_id")
    private List<PerguntasModel> perguntas;

    @Column(name = "certificado")
    @Enumerated(EnumType.STRING)
    private NivelCertificadoEnum certificado;

    @OneToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaModel empresa;

    @NotNull
    @Enumerated(EnumType.STRING) // Mapeamento do enum como string
    private RespostasEnum respostas;

    @Column(name = "pontuacao_final")
    private Integer pontuacaoFinal;

    @Column(name = "pontuacao_social")
    private Integer pontuacaoSocial;

    @Column(name = "pontuacao_ambiental")
    private Integer pontuacaoAmbiental;

    @Column(name = "pontuacao_economico")
    private Integer pontuacaoEconomico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_respostas")
    private Date dataRespostas;

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

    public @NotNull RespostasEnum getRespostas() {
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

    public Integer getPontuacaoEconomico() {
        return pontuacaoEconomico;
    }

    public void setPontuacaoEconomico(Integer pontuacaoEconomico) {
        this.pontuacaoEconomico = pontuacaoEconomico;
    }

    public Date getDataRespostas() {
        return dataRespostas;
    }

    public void setDataRespostas(Date dataRespostas) {
        this.dataRespostas = dataRespostas;
    }
}
