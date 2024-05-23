package com.ambientese.grupo5.Model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "Certificado")
public class CertificadoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificado_id")
    private long id;

    @NotNull
    @NotBlank
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao) {
        this.descricao = descricao;
    }
}
