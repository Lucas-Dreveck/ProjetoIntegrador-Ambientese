package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.DTO.QuestionarioResponse;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import com.ambientese.grupo5.Repository.PerguntasRepository;
import com.ambientese.grupo5.Repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BuscarPerguntasDoBancoService {

    private final PerguntasRepository perguntasRepository;
    private final FormularioRepository formularioRepository;
    private final RespostaRepository respostaRepository;
    private final Random random;

    @Autowired
    public BuscarPerguntasDoBancoService(PerguntasRepository perguntasRepository, FormularioRepository formularioRepository, RespostaRepository respostaRepository) {
        this.perguntasRepository = perguntasRepository;
        this.formularioRepository = formularioRepository;
        this.respostaRepository = respostaRepository;
        this.random = new Random();
    }

    public QuestionarioResponse buscarPerguntasDoBanco(Boolean isNewForm, Long empresaId) {
        if (isNewForm) {
            Optional<FormularioModel> latestForm = formularioRepository.findIncompleteByEmpresaId(empresaId);
            if (latestForm.isPresent()) {
                respostaRepository.deleteAll(latestForm.get().getRespostas());
                formularioRepository.delete(latestForm.get());
            }

            List<PerguntasModel> todasPerguntas = new ArrayList<>();
            for (EixoEnum eixo : EixoEnum.values()) {
                List<PerguntasModel> perguntasEixo = perguntasRepository.findByEixo(eixo);

                Collections.shuffle(perguntasEixo, random);

                todasPerguntas.addAll(perguntasEixo.subList(0, Math.min(perguntasEixo.size(), 10)));
            }
            if (todasPerguntas.size() != 30) {
                throw new RuntimeException("Não foi possível encontrar o número necessário de perguntas");
            }
            return new QuestionarioResponse(todasPerguntas, null);
        } else {
            Optional<FormularioModel> latestForm = formularioRepository.findIncompleteByEmpresaId(empresaId);
            if (latestForm.isPresent()) {
                List<FormularioRequest> formularioRequests = latestForm.get().getRespostas().stream()
                    .map(resposta -> new FormularioRequest(
                        resposta.getPergunta().getId(),
                        resposta.getPergunta().getDescricao(),
                        resposta.getResposta(),
                        resposta.getPergunta().getEixo(),
                        latestForm.get().getId()
                    ))
                    .collect(Collectors.toList());
                return new QuestionarioResponse(null, formularioRequests);
            } else {
                throw new RuntimeException("Não foi possível encontrar o formulário");
            }
        }
    }
}
