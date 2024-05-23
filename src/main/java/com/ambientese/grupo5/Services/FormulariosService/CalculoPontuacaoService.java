package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculoPontuacaoService {

    public void calcularPontuacoes(List<FormularioRequest> respostas, FormularioModel formulario) {
        int totalPerguntas = respostas.size();
        int perguntasConforme = 0;
        int pontuacaoSocial = 0;
        int pontuacaoAmbiental = 0;
        int pontuacaoEconomico = 0;

        for (FormularioRequest resposta : respostas) {
            if (resposta.getRespostaUsuario() != null && resposta.getRespostaUsuario() == RespostasEnum.Conforme) {
                perguntasConforme++;
                switch (resposta.getPerguntaEixo()) {
                    case Social:
                        pontuacaoSocial++;
                        break;
                    case Ambiental:
                        pontuacaoAmbiental++;
                        break;
                    case Econômico:
                        pontuacaoEconomico++;
                        break;
                    default:
                        break;
                }
            }
        }

        double pontuacaoFinal = (double) perguntasConforme / totalPerguntas * 100.0;

        formulario.setPontuacaoFinal((int) pontuacaoFinal);
        formulario.setPontuacaoSocial(pontuacaoSocial);
        formulario.setPontuacaoAmbiental(pontuacaoAmbiental);
        formulario.setPontuacaoEconomico(pontuacaoEconomico);
    }
}
