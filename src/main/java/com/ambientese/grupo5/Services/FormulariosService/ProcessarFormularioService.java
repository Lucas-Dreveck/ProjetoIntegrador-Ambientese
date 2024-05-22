package com.ambientese.grupo5.Services.FormulariosService;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Model.RespostaModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import com.ambientese.grupo5.Repository.PerguntasRepository;
import com.ambientese.grupo5.Repository.RespostaRepository;

import jakarta.transaction.Transactional;

@Service
public class ProcessarFormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private PerguntasRepository perguntasRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public FormularioModel criarProcessarEGerarCertificado(Long empresaId, List<FormularioRequest> formularioRequestList) {
        int totalPerguntas = formularioRequestList.size();
        int perguntasConforme = 0;
        int pontuacaoSocial = 0;
        int pontuacaoAmbiental = 0;
        int pontuacaoGovernamental = 0;

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
                        pontuacaoGovernamental++;
                        break;
                    default:
                        break;
                }
            }
            if (resposta.getRespostaUsuario() == null && resposta.getRespostaUsuario() == RespostasEnum.NãoSeAdequa){
                totalPerguntas--;
            }
        }

        double pontuacaoFinal = (double) perguntasConforme / totalPerguntas * 100.0;

        // Gerar certificado
        NivelCertificadoEnum nivelCertificado = calcularNivelCertificado(pontuacaoFinal);

        // Verificar a existência da empresa
        EmpresaModel empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        // Criar o objeto de modelo de formulário e definir suas propriedades
        FormularioModel formularioModel = new FormularioModel();
        formularioModel.setPontuacaoFinal((int) pontuacaoFinal);
        formularioModel.setPontuacaoSocial(pontuacaoSocial);
        formularioModel.setPontuacaoAmbiental(pontuacaoAmbiental);
        formularioModel.setPontuacaoGovernamental(pontuacaoGovernamental);
        formularioModel.setCertificado(nivelCertificado);
        formularioModel.setEmpresa(empresa);
        formularioModel.setDataRespostas(new Date());

        // Criação e associação das respostas
        List<RespostaModel> respostaModels = new ArrayList<>();
        for (FormularioRequest entry : formularioRequestList) {
            RespostaModel respostaModel = new RespostaModel();
            PerguntasModel pergunta = perguntasRepository.findById(entry.getPerguntaId()).orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
            respostaModel.setFormulario(formularioModel);
            respostaModel.setPergunta(pergunta);
            respostaModel.setResposta(entry.getRespostaUsuario());
            respostaModels.add(respostaModel);
        }

        formularioModel.setRespostas(respostaModels);
        formularioModel = formularioRepository.save(formularioModel);
        respostaRepository.saveAll(respostaModels);

        return formularioModel;
    }

    NivelCertificadoEnum calcularNivelCertificado(double pontuacaoFinal) {
        if (pontuacaoFinal >= 100) {
            return NivelCertificadoEnum.Ouro;
        } else if (pontuacaoFinal >= 75.1) {
            return NivelCertificadoEnum.Prata;
        } else {
            return NivelCertificadoEnum.Bronze;
        }
    }
}