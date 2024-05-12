package com.ambientese.grupo5.Services.EmpresaService;

import com.ambientese.grupo5.DTO.EmpresaRequest;
import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CriarEmpresaService {

    private final EmpresaRepository empresaRepository;
    private final ValidacaoCamposObrigatoriosService validacaoCamposObrigatoriosService;
    private final ValidacaoCNPJService validacaoCNPJService;
    private final MapearEmpresaService mapearEmpresaService;

    @Autowired
    public CriarEmpresaService(EmpresaRepository empresaRepository, ValidacaoCamposObrigatoriosService validacaoCamposObrigatoriosService,
                               ValidacaoCNPJService validacaoCNPJService, MapearEmpresaService mapearEmpresaService) {
        this.empresaRepository = empresaRepository;
        this.validacaoCamposObrigatoriosService = validacaoCamposObrigatoriosService;
        this.validacaoCNPJService = validacaoCNPJService;
        this.mapearEmpresaService = mapearEmpresaService;
    }

    public EmpresaModel criarEmpresa(EmpresaRequest empresaRequest) {
        validacaoCamposObrigatoriosService.validarCamposObrigatorios(empresaRequest);
        validacaoCNPJService.validarCnpjUnico(empresaRequest.getCnpj(), null);
        EmpresaModel empresaModel = new EmpresaModel();
        mapearEmpresaService.mapearEmpresa(empresaModel, empresaRequest);
        return empresaRepository.save(empresaModel);
    }
}
