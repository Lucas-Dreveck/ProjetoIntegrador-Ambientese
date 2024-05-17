package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Repository.PerguntasRepository;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormularioService {

    private final PerguntasRepository perguntasRepository;

    public FormularioService(PerguntasRepository perguntasRepository) {
        this.perguntasRepository = perguntasRepository;
    }

    public FormularioModel criarFormularioAleatorio(int perguntasPorEixo) {
        FormularioModel formulario = new FormularioModel();

        // Lista para armazenar perguntas selecionadas
        List<PerguntasModel> perguntasSelecionadas = new ArrayList<>();

        // Obter todas as perguntas do repositório
        List<PerguntasModel> todasPerguntas = perguntasRepository.findAll();

        // Iterar pelos eixos
        for (EixoEnum eixo : EixoEnum.values()) {
            // Filtrar as perguntas por eixo
            List<PerguntasModel> perguntasPorEixo = todasPerguntas.stream()
                    .filter(pergunta -> pergunta.getEixo() == eixo)
                    .collect(Collectors.toList());

            // Embaralhar as perguntas
            Collections.shuffle(perguntasPorEixo);

            // Adicionar um número específico de perguntas por eixo às perguntas selecionadas
            perguntasSelecionadas.addAll(perguntasPorEixo.subList(0, Math.min(perguntasPorEixo.size(), perguntasPorEixo.size())));
        }

        // Embaralhar novamente as perguntas selecionadas
        Collections.shuffle(perguntasSelecionadas);

        // Selecionar as primeiras perguntasPorEixo * número de eixos perguntas para o formulário
        perguntasSelecionadas = perguntasSelecionadas.subList(0, Math.min(perguntasPorEixo * EixoEnum.values().length, perguntasSelecionadas.size()));

        // Definir as perguntas selecionadas no formulário
        // Supondo que você tenha um método setPerguntas no FormularioModel
        formulario.setPerguntas(perguntasSelecionadas);

        // Definir a data de respostas como a data atual
        formulario.setDataRespostas(new Date());

        return formulario;
    }
}
