package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import com.ambientese.grupo5.Services.PerguntasService.ListarPerguntasPorEixoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CriarFormularioService {

    private final FormularioRepository formularioRepository;

    @Autowired
    public CriarFormularioService(ListarPerguntasPorEixoService listarPerguntasPorEixoService, FormularioRepository formularioRepository) {
        this.formularioRepository = formularioRepository;
    }

    public FormularioModel criarProcessarEGerarCertificado(Map<Long, RespostasEnum> respostasMap) {
        int totalPerguntas = respostasMap.size();
        int perguntasConforme = 0;
        int pontuacaoSocial = 0;
        int pontuacaoAmbiental = 0;
        int pontuacaoEconomico = 0;

// Verificar as respostas e calcular pontuações
        for (RespostasEnum resposta : respostasMap.values()) {
            if (resposta == RespostasEnum.Conforme) {
                perguntasConforme++;

                // Aqui você pode adicionar a lógica para calcular as pontuações
                // Considerando a categoria da pergunta (eixo)
                switch (resposta) {
                    case Conforme:
                        pontuacaoSocial++;
                        break;
                    case NãoConforme:
                        pontuacaoAmbiental++;
                        break;
                    case NãoSeAdequa:
                        pontuacaoEconomico++;
                        break;
                    default:
                        // Lógica para outros casos, se aplicável
                        break;
                }
            }
        }


        double pontuacaoFinal = (double) perguntasConforme / totalPerguntas * 100.0;

        // Gerar certificado
        NivelCertificadoEnum nivelCertificado = calcularNivelCertificado(pontuacaoFinal);

        // Criar o objeto de modelo de formulário e definir suas propriedades
        FormularioModel formularioModel = new FormularioModel();

        // Crie uma lista de respostas associadas às perguntas
        List<RespostasEnum> respostas = new ArrayList<>();
        for (Map.Entry<Long, RespostasEnum> entry : respostasMap.entrySet()) {
            respostas.add(entry.getValue());
        }
        formularioModel.setRespostas(respostas); // Armazena as respostas no formulário

        formularioModel.setPontuacaoFinal((int) pontuacaoFinal);
        formularioModel.setPontuacaoSocial(pontuacaoSocial);
        formularioModel.setPontuacaoAmbiental(pontuacaoAmbiental);
        formularioModel.setPontuacaoEconomico(pontuacaoEconomico);
        formularioModel.setCertificado(nivelCertificado);

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
