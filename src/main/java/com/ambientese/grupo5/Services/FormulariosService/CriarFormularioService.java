package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Services.PerguntasService.ListarPerguntasPorEixoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CriarFormularioService {

    private final ListarPerguntasPorEixoService listarPerguntasPorEixoService;
    private final ObterFormularioService obterFormularioService;
    private final CalculoPontuacaoService calculoPontuacaoService;

    @Autowired
    public CriarFormularioService(ListarPerguntasPorEixoService listarPerguntasPorEixoService,
                                  ObterFormularioService obterFormularioService,
                                  CalculoPontuacaoService calculoPontuacaoService) {
        this.listarPerguntasPorEixoService = listarPerguntasPorEixoService;
        this.obterFormularioService = obterFormularioService;
        this.calculoPontuacaoService = calculoPontuacaoService;
    }

    public List<FormularioRequest> criarFormularioComEixos(List<EixoEnum> eixos, int perguntasPorEixo) {
        List<FormularioRequest> formularioRequestList = new ArrayList<>();

        for (EixoEnum eixo : eixos) {
            List<PerguntasModel> perguntas = listarPerguntasPorEixoService.listarPerguntasPorEixo(eixo);
            Collections.shuffle(perguntas);
            for (int i = 0; i < Math.min(perguntasPorEixo, perguntas.size()); i++) {
                PerguntasModel pergunta = perguntas.get(i);
                FormularioRequest formularioRequest = new FormularioRequest();
                formularioRequest.setNumeroPergunta(pergunta.getId());
                formularioRequest.setPerguntaDescricao(pergunta.getDescricao());
                formularioRequest.setPerguntaEixo(pergunta.getPerguntasEixo());
                formularioRequest.setRespostaUsuario(null); // Definir respostas como nulas inicialmente
                formularioRequestList.add(formularioRequest);
            }
        }

        return formularioRequestList;
    }

    public void salvarRespostasECalcularPontuacao(List<FormularioRequest> respostas, long formularioId) {
        FormularioModel formulario = obterFormularioService.obterFormularioPorId(formularioId);
        calculoPontuacaoService.calcularPontuacoes(respostas, formulario);
        obterFormularioService.salvarFormulario(formulario);

    }
}