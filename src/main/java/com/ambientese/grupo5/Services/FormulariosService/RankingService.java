package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.DTO.FormularioRanking;
import com.ambientese.grupo5.Repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private final FormularioRepository formularioRepository;

    @Autowired
    public RankingService(FormularioRepository formularioRepository) {
        this.formularioRepository = formularioRepository;
    }

    public FormularioModel buscarPorId(Long id) {
        return formularioRepository.findById(id).orElse(null);
    }

    public List<FormularioRanking> classificarPorPontuacao() {
        List<FormularioModel> formularios = formularioRepository.findLatestByEmpresaOrderByPontuacaoFinalDesc();
        return formularios.stream().map(formulario -> new FormularioRanking(
                formulario.getId(),
                formulario.getEmpresa().getNomeFantasia(),
                formulario.getCertificado(),
                formulario.getPontuacaoFinal(),
                formulario.getPontuacaoSocial(),
                formulario.getPontuacaoAmbiental(),
                formulario.getPontuacaoGovernamental(),
                formulario.getDataRespostas()
        )).collect(Collectors.toList());
    }
    
    public List<FormularioModel>classificarPorEixoAmbiental(){
        return formularioRepository.findAllByOrderByPontuacaoAmbientalAsc();
    }
    public List<FormularioModel>classificarPorEixoSocial (){
        return formularioRepository.findAllByOrderByPontuacaoSocialAsc();
    }
    public List<FormularioModel>classificarPorEixoGovernamental (){
        return formularioRepository.findAllByOrderByPontuacaoGovernamentalAsc();
    }
}
