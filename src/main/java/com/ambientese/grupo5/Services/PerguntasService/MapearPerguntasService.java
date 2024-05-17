package com.ambientese.grupo5.Services.PerguntasService;

import com.ambientese.grupo5.DTO.PerguntasRequest;
import com.ambientese.grupo5.Model.PerguntasModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapearPerguntasService {

    public List<PerguntasModel> mapearPerguntas(List<PerguntasRequest> perguntas) {
        List<PerguntasModel> perguntasMapeadas = new ArrayList<>();
        for (PerguntasRequest perguntaRequest : perguntas) {
            PerguntasModel pergunta = new PerguntasModel();
            pergunta.setDescricao(perguntaRequest.getDescricao());
            pergunta.setEixo(perguntaRequest.getEixo());
            perguntasMapeadas.add(pergunta);
        }
        return perguntasMapeadas;
    }
}
