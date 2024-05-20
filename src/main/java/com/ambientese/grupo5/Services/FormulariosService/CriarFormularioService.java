package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Services.PerguntasService.ListarPerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriarFormularioService {

    private final ListarPerguntasService listarPerguntasService;

    @Autowired
    public CriarFormularioService(ListarPerguntasService listarPerguntasService) {
        this.listarPerguntasService = listarPerguntasService;
    }

    public List<FormularioRequest> criarFormulario() {
        List<FormularioRequest> formularioRequestList = new ArrayList<>();

        // Obtém as perguntas do serviço de listagem
        List<PerguntasModel> perguntas = listarPerguntasService.listarPerguntas();

        // Gera o formulário com base nas perguntas obtidas
        for (PerguntasModel pergunta : perguntas) {
            FormularioRequest formularioRequest = new FormularioRequest();
            formularioRequest.setNumeroPergunta(pergunta.getId());
            formularioRequest.setPerguntaDescricao(pergunta.getDescricao());
            formularioRequest.setRespostaUsuario(null); // Definir respostas como nulas inicialmente
            formularioRequestList.add(formularioRequest);
        }

        return formularioRequestList;
    }

    // Método para calcular pontuação com base nas respostas do usuário
    public double calcularPontuacao(List<FormularioRequest> respostas) {
        int totalPerguntas = respostas.size();
        int perguntasConforme = 0;

        for (FormularioRequest resposta : respostas) {
            // Verifica se a resposta do usuário é Conforme
            if (resposta.getRespostaUsuario() != null && resposta.getRespostaUsuario() == RespostasEnum.Conforme) {
                perguntasConforme++;
            }
        }

        // Calcula a pontuação com base nas perguntas respondidas conforme e no total de perguntas
        if (totalPerguntas > 0) {
            return ((double) perguntasConforme / totalPerguntas) * 100.0;
        } else {
            return 0.0; // Retorna 0 se não houver perguntas no formulário
        }
    }
}
