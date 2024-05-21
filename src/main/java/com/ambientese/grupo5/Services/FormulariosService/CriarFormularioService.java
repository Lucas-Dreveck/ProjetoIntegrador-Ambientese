package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
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

    @Autowired
    public CriarFormularioService(ListarPerguntasPorEixoService listarPerguntasPorEixoService) {
        this.listarPerguntasPorEixoService = listarPerguntasPorEixoService;
    }

    public List<FormularioRequest> criarFormularioComEixos(List<EixoEnum> eixos, int perguntasPorEixo) {
        List<FormularioRequest> formularioRequestList = new ArrayList<>();

        for (EixoEnum eixo : eixos) {
            List<PerguntasModel> perguntas = listarPerguntasPorEixoService.listarPerguntasPorEixo(eixo);

            // Se houver mais perguntas do que o necessário, embaralha e pega as primeiras
            if (perguntas.size() > perguntasPorEixo) {
                Collections.shuffle(perguntas);
                perguntas = perguntas.subList(0, perguntasPorEixo);
            }

            // Gera o formulário com base nas perguntas obtidas
            for (PerguntasModel pergunta : perguntas) {
                FormularioRequest formularioRequest = new FormularioRequest();
                formularioRequest.setNumeroPergunta(pergunta.getId());
                formularioRequest.setPerguntaDescricao(pergunta.getDescricao());
                formularioRequest.setRespostaUsuario(null); // Definir respostas como nulas inicialmente
                formularioRequestList.add(formularioRequest);
            }
        }

        return formularioRequestList;
    }
}
