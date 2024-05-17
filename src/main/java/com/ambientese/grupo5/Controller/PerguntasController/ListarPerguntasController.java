package com.ambientese.grupo5.Controller.PerguntasController;

import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Services.PerguntasService.ListarPerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Perguntas")
public class ListarPerguntasController {

    private final ListarPerguntasService listarPerguntasService;

    @Autowired
    public ListarPerguntasController(ListarPerguntasService listarPerguntasService) {
        this.listarPerguntasService = listarPerguntasService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<PerguntasModel>> listarPerguntas() {
        List<PerguntasModel> perguntas = listarPerguntasService.listarPerguntas();
        return new ResponseEntity<>(perguntas, HttpStatus.OK);
    }
}
