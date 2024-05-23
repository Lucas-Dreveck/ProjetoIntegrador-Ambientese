package com.ambientese.grupo5.Model;

<<<<<<< HEAD
import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
=======
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
>>>>>>> develop-empresa-front
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Formulario")
public class FormularioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formulario_id")
    private long id;

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<RespostaModel> respostas;

    @OneToOne
    @JoinColumn(name = "certificado_id")
    private CertificadoModel certificado;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaModel empresa;

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

<<<<<<< HEAD
    public NivelCertificadoEnum getCertificado() {
=======
    public PerguntasModel getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntasModel> perguntas) {
        this.perguntas = (PerguntasModel) perguntas;
    }


    public CertificadoModel getCertificado() {
>>>>>>> develop-empresa-front
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

    public List<RespostaModel> getRespostas() {
        return respostas;
    }

<<<<<<< HEAD
    public void setRespostas(List<RespostaModel> respostas) {
=======
    public void setRespostas(RespostasEnum respostas) {
>>>>>>> develop-empresa-front
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
