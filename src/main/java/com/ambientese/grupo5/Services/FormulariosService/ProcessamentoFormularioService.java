/*
package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProcessamentoFormularioService {

    private final FormularioRepository formularioRepository;
    private final CalculoPontuacaoService calculoPontuacaoService;
    private final GeracaoCertificadoService geracaoCertificadoService;

    @Autowired
    public ProcessamentoFormularioService(FormularioRepository formularioRepository,
                                          CalculoPontuacaoService calculoPontuacaoService,
                                          GeracaoCertificadoService geracaoCertificadoService,
                                          AtualizarRankingService atualizarRankingService) {
        this.formularioRepository = formularioRepository;
        this.calculoPontuacaoService = calculoPontuacaoService;
        this.geracaoCertificadoService = geracaoCertificadoService;
    }

    public void processarFormulario(FormularioRequest formularioRequest) {
        FormularioModel formulario = new FormularioModel();

        // Definir o ID da pergunta e a resposta no objeto FormularioModel
        formulario.setPerguntas(formularioRequest.getNumeroPergunta());
        formulario.setRespostas(formularioRequest.getRespostaUsuario());

        // Calcular pontuações
        calculoPontuacaoService.calcularPontuacoes(Collections.singletonList(formularioRequest), formulario);

        // Gerar certificado
        NivelCertificadoEnum nivelCertificado = geracaoCertificadoService.calcularNivelCertificado(formulario.getPontuacaoFinal());
        formulario.setCertificado(nivelCertificado);

        // Salvar o formulário no banco de dados
        formularioRepository.save(formulario);
    }

}
*/
