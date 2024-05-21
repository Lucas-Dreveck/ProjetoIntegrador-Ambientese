/*
package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtualizarRankingService {

    private final FormularioRepository formularioRepository;

    @Autowired
    public AtualizarRankingService(FormularioRepository formularioRepository) {
        this.formularioRepository = formularioRepository;
    }

    public List<FormularioModel> obterRanking() {
        return formularioRepository.findAllOrderByPontuacaoFinal();
    }
}
*/
