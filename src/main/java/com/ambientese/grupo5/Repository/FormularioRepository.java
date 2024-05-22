package com.ambientese.grupo5.Repository;

import com.ambientese.grupo5.Model.FormularioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormularioRepository extends JpaRepository<FormularioModel, Long> {
    List<FormularioModel> findAllByOrderByPontuacaoFinalAsc();
    List<FormularioModel> findAllByOrderByPontuacaoSocialAsc();
    List<FormularioModel> findAllByOrderByPontuacaoAmbientalAsc();
    List<FormularioModel> findAllByOrderByPontuacaoGovernamentalAsc();
}
