package com.ambientese.grupo5.Controller.EmpresaController;

import com.ambientese.grupo5.DTO.EmpresaRequest;
import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Services.EmpresaService.CriarEmpresaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/Empresa")
@Validated
public class CriarEmpresaController {

    private final CriarEmpresaService criarEmpresa;

    public CriarEmpresaController(CriarEmpresaService criarEmpresa)
    {
        this.criarEmpresa = criarEmpresa;
    }
    @PostMapping("/Criar")
    public EmpresaModel criarEmpresa (@Valid @RequestBody EmpresaRequest empresaRequest) {
        return criarEmpresa.criarEmpresa(empresaRequest);
    }

}