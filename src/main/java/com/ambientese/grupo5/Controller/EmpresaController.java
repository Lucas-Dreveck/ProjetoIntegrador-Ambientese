package com.ambientese.grupo5.Controller;

import com.ambientese.grupo5.DTO.EmpresaRequest;
import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import com.ambientese.grupo5.Services.EmpresaService;
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
