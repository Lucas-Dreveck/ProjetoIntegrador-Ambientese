package com.Ambientese.Empresa.Controller;

import com.Ambientese.Empresa.DTO.EmpresaRequest;
import com.Ambientese.Empresa.Model.EmpresaModel;
import com.Ambientese.Empresa.Repository.EmpresaRepository;
import com.Ambientese.Empresa.Services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Empresa")
@Validated
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<EmpresaModel> listarEmpresa() {
        return empresaRepository.findAll();
    }

    @PostMapping
    public EmpresaModel criarEmpresa(@Valid @RequestBody EmpresaRequest empresaRequest) {
        return empresaService.criarEmpresa(empresaRequest);
    }

    @PutMapping("/{id}")
    public EmpresaModel atualizarEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaRequest empresaRequest) {
        return empresaService.atualizarEmpresa(id, empresaRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable Long id) {
        return empresaRepository.findById(id).map(empresaModel -> {
            empresaRepository.delete(empresaModel);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
