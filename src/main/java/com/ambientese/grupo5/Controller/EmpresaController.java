package com.ambientese.grupo5.Controller;

import com.ambientese.grupo5.DTO.EmpresaRequest;
import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import com.ambientese.grupo5.Services.EmpresaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Empresa")
@Validated
public class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaRepository empresaRepository, EmpresaService empresaService) {
        this.empresaRepository = empresaRepository;
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaModel>> getAllEmpresas() {
        List<EmpresaModel> empresaModels = empresaService.getAllEmpresas();
        return ResponseEntity.ok(empresaModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEmpresaPorId(@PathVariable Long id) {
        Optional<EmpresaModel> empresa = empresaRepository.findById(id);
        return empresa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmpresaModel>> buscarEmpresas(
            @RequestParam(required = false) String nomeFantasia,
            @RequestParam(required = false) String razaoSocial) {
        List<EmpresaModel> empresas;
        if (nomeFantasia != null && !nomeFantasia.isEmpty()) {
            empresas = empresaRepository.findFirst10ByNomeFantasiaContainingIgnoreCase(nomeFantasia);
        } else if (razaoSocial != null && !razaoSocial.isEmpty()) {
            empresas = empresaRepository.findFirst10ByRazaoSocialContainingIgnoreCase(razaoSocial);
        } else {
            Page<EmpresaModel> empresasPage = empresaRepository.findAll(PageRequest.of(0, 25));
            empresas = empresasPage.getContent();
        }

        return ResponseEntity.ok(empresas);
    }


    @PostMapping("/Criar")
    public EmpresaModel criarEmpresa (@Valid @RequestBody EmpresaRequest empresaRequest) {
        return empresaService.criarEmpresa(empresaRequest);
    }

    @PutMapping("/Atualizar/{id}")
    public EmpresaModel atualizarEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaRequest empresaRequest) {
        return empresaService.atualizarEmpresa(id, empresaRequest);
    }

    @DeleteMapping("/Deletar/{id}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable Long id) {
        empresaService.deleteEmpresa(id);
        return ResponseEntity.ok().build();
    }
}
