package com.ambientese.grupo5.Repository;

import com.ambientese.grupo5.Model.FormularioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormularioRepository extends JpaRepository<FormularioModel, Long> {
<<<<<<< HEAD
    List<FormularioModel> findAllByOrderByPontuacaoFinalAsc();
    List<FormularioModel> findAllByOrderByPontuacaoSocialAsc();
    List<FormularioModel> findAllByOrderByPontuacaoAmbientalAsc();
    List<FormularioModel> findAllByOrderByPontuacaoGovernamentalAsc();
=======
>>>>>>> develop-empresa-front
}
