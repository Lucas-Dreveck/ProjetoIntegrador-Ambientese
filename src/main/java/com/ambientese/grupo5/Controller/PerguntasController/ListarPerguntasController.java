package com.ambientese.grupo5.Controller.PerguntasController;

import com.ambientese.grupo5.Model.Enums.EixoEnum;
import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Services.PerguntasService.ListarPerguntasPorEixoService;
import com.ambientese.grupo5.Services.PerguntasService.ListarPerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/Perguntas")
public class ListarPerguntasController {

    @Autowired
    private ListarPerguntasService listarPerguntasService;

    @Autowired
    private ListarPerguntasPorEixoService listarPerguntasPorEixoService;

    @GetMapping("/search")
    public List<PerguntasModel> listarPerguntas() {
        return listarPerguntasService.listarPerguntas();
    }

    @GetMapping("/eixo/{eixo}")
    public List<PerguntasModel> listarPerguntasPorEixo(@PathVariable EixoEnum eixo) {
        return listarPerguntasPorEixoService.listarPerguntasPorEixo(eixo);
    }
}
