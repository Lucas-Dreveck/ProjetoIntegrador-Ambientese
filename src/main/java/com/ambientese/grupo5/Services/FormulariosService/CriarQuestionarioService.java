package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriarQuestionarioService {

    private final FormularioRepository formularioRepository;

    @Autowired
    public CriarQuestionarioService(FormularioRepository formularioRepository) {
        this.formularioRepository = formularioRepository;
    }

    public FormularioModel criarProcessarEGerarCertificado(List<FormularioRequest> formularioRequestList) {
        int totalPerguntas = formularioRequestList.size();
        int perguntasConforme = 0;
        int pontuacaoSocial = 0;
        int pontuacaoAmbiental = 0;
        int pontuacaoEconomico = 0;

        // Verificar as respostas e calcular pontuações
        for (FormularioRequest resposta : formularioRequestList) {
            if (resposta.getRespostaUsuario() != null && resposta.getRespostaUsuario() == RespostasEnum.Conforme) {
                perguntasConforme++;
                switch (resposta.getPerguntaEixo()) {
                    case Social:
                        pontuacaoSocial++;
                        break;
                    case Ambiental:
                        pontuacaoAmbiental++;
                        break;
                    case Governamental:
                        pontuacaoEconomico++;
                        break;
                    default:
                        break;
                }
            }
        }

        double pontuacaoFinal = (double) perguntasConforme / totalPerguntas * 100.0;

        // Gerar certificado
        NivelCertificadoEnum nivelCertificado = calcularNivelCertificado(pontuacaoFinal);

        // Criar o objeto de modelo de formulário e definir suas propriedades
        FormularioModel formularioModel = new FormularioModel();
        formularioModel.setPontuacaoFinal((int) pontuacaoFinal);
        formularioModel.setPontuacaoSocial(pontuacaoSocial);
        formularioModel.setPontuacaoAmbiental(pontuacaoAmbiental);
        formularioModel.setPontuacaoEconomico(pontuacaoEconomico);
        formularioModel.setCertificado(nivelCertificado);

        // Crie uma lista de respostas associadas às perguntas
        List<RespostasEnum> respostas = new ArrayList<>();
        for (FormularioRequest entry : formularioRequestList) {
            respostas.add(entry.getRespostaUsuario());
        }
        formularioModel.setRespostas(respostas);

        // Salvar o formulário
        formularioRepository.save(formularioModel);

        return formularioModel;
    }

    private NivelCertificadoEnum calcularNivelCertificado(double pontuacaoFinal) {
        if (pontuacaoFinal >= 100) {
            return NivelCertificadoEnum.Ouro;
        } else if (pontuacaoFinal >= 75.1) {
            return NivelCertificadoEnum.Prata;
        } else {
            return NivelCertificadoEnum.Bronze;
        }
    }
}
