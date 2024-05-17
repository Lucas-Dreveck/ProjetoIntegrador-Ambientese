package com.ambientese.grupo5.Controller.FormularioController;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.DTO.PerguntasRequest;
import com.ambientese.grupo5.Services.FormulariosService.CriarFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formulario")
public class CriarFormularioController {

    private final CriarFormularioService criarFormularioService;

    @Autowired
    public CriarFormularioController(CriarFormularioService criarFormularioService) {
        this.criarFormularioService = criarFormularioService;
    }

    @PostMapping("/add")
    public List<FormularioRequest> criarFormulario(@RequestBody List<PerguntasRequest> perguntas) {
        return criarFormularioService.criarFormulario(perguntas);
    }
}
