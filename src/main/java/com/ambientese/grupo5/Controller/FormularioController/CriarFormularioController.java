package com.ambientese.grupo5.Controller.FormularioController;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
import com.ambientese.grupo5.Services.FormulariosService.CriarFormularioService;
/*import com.ambientese.grupo5.Services.FormulariosService.ProcessamentoFormularioService;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formularios")
public class CriarFormularioController {

    private final CriarFormularioService criarFormularioService;
    /*private final ProcessamentoFormularioService processamentoFormularioService;*/

    @Autowired
    public CriarFormularioController(CriarFormularioService criarFormularioService) {
        this.criarFormularioService = criarFormularioService;
    }

    @PostMapping("/eixos")
    public ResponseEntity<List<FormularioRequest>> criarFormularioComEixos(@RequestBody List<EixoEnum> eixos, @RequestParam int perguntasPorEixo) {
        List<FormularioRequest> formulario = criarFormularioService.criarFormularioComEixos(eixos, perguntasPorEixo);
        return ResponseEntity.ok(formulario);
    }

    @PostMapping("/respostas/{formularioId}")
    public ResponseEntity<Void> salvarRespostasECalcularPontuacao(@RequestBody List<FormularioRequest> respostas, @PathVariable long formularioId) {
        criarFormularioService.salvarRespostasECalcularPontuacao(respostas, formularioId);
        return ResponseEntity.ok().build();
    }

    /*@PostMapping("/processar")
    public ResponseEntity<String> salvarFormularioEGerarCertificado(@RequestBody FormularioRequest formularioRequest) {
        try {
            processamentoFormularioService.processarFormulario(formularioRequest);
            return ResponseEntity.ok("Formulário processado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao processar o formulário: \{e.getMessage()}");
        }
    }*/
}
