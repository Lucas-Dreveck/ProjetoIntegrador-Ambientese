package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Model.FormularioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalvarFormularioService {

    private final ObterFormularioService obterFormularioService;
    private final CalculoPontuacaoService calculoPontuacaoService;

    @Autowired
    public SalvarFormularioService(ObterFormularioService obterFormularioService,
                                   CalculoPontuacaoService calculoPontuacaoService) {
        this.obterFormularioService = obterFormularioService;
        this.calculoPontuacaoService = calculoPontuacaoService;
    }

    public void salvarRespostasECalcularPontuacao(List<FormularioRequest> respostas, long formularioId, EmpresaModel empresaSelecionada) {
        FormularioModel formulario = obterFormularioService.obterFormularioPorId(formularioId);
        formulario.setEmpresa(empresaSelecionada);
        calculoPontuacaoService.calcularPontuacoes(respostas, formulario);
        obterFormularioService.salvarFormulario(formulario);
    }
}
