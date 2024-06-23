package com.ambientese.grupo5.Services.PerguntasService;

import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Repository.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPerguntasService {

    @Autowired
    private PerguntasRepository perguntasRepository;

    public List<PerguntasModel> listarPerguntas() {
        return perguntasRepository.findAll();
    }
}
