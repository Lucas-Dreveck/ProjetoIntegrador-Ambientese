package com.ambientese.grupo5.Controller.FormularioController;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Services.FormulariosService.CriarFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formularios")
public class CriarFormularioController {

    private final CriarFormularioService formularioService;

    @Autowired
    public CriarFormularioController(CriarFormularioService formularioService) {
        this.formularioService = formularioService;
    }

    @PostMapping("/criar")
    public FormularioModel criarFormulario(@RequestBody List<FormularioRequest> formularioRequestList) {
        return formularioService.criarProcessarEGerarCertificado(formularioRequestList);
    }
}
