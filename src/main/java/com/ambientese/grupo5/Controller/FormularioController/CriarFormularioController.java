package com.ambientese.grupo5.Controller.FormularioController;

import com.ambientese.grupo5.DTO.CriarFormularioRequest;
import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
import com.ambientese.grupo5.Services.FormulariosService.CriarFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formularios")
public class CriarFormularioController {

    private final CriarFormularioService criarFormularioService;

    @Autowired
    public CriarFormularioController(CriarFormularioService criarFormularioService) {
        this.criarFormularioService = criarFormularioService;
    }

    @PostMapping("/eixos")
    public ResponseEntity<List<FormularioRequest>> criarFormularioComEixos(@RequestBody CriarFormularioRequest request) {
        List<EixoEnum> eixosEnum = request.getEixos();
        int perguntasPorEixo = request.getPerguntasPorEixo();
        List<FormularioRequest> formulario = criarFormularioService.criarFormularioComEixos(eixosEnum, perguntasPorEixo);
        return ResponseEntity.ok(formulario);
    }

}
