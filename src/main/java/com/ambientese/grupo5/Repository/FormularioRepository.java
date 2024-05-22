package com.ambientese.grupo5.Repository;

import com.ambientese.grupo5.Model.FormularioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormularioRepository extends JpaRepository<FormularioModel, Long> {

    @Query("SELECT f FROM FormularioModel f ORDER BY f.pontuacaoFinal")
    List<FormularioModel> findAllOrderByPontuacaoFinal();

    @Query("SELECT f FROM FormularioModel f ORDER BY f.pontuacaoSocial")
    List<FormularioModel> findAllOrderByPontuacaoSocial();

    @Query("SELECT f FROM FormularioModel f ORDER BY f.pontuacaoAmbiental")
    List<FormularioModel> findAllOrderByPontuacaoAmbiental();

    @Query("SELECT f FROM FormularioModel f ORDER BY f.pontuacaoGovernamental")
    List<FormularioModel> findAllOrderByPontuacaoGovernamental();
}
