package com.ambientese.grupo5.Services.EmpresaService;

import com.ambientese.grupo5.DTO.EmpresaRequest;
import com.ambientese.grupo5.Exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoCamposObrigatoriosService {

    private final ValidacaoCNPJService validacaoCNPJService;
    private final ValidacaoTelefoneService validacaoTelefoneService;
    private final ValidacaoCEPService validacaoCEPService;
    private final ValidacaoEmailService validacaoEmailService;
    private final ValidacaoisEmptyorNullService validacaoisEmptyorNullService;
    private final ValidacaoEnderecoService validacaoEnderecoService;


    @Autowired
    public ValidacaoCamposObrigatoriosService(ValidacaoCNPJService validacaoCNPJService,
                                              ValidacaoTelefoneService validacaoTelefoneService, ValidacaoCEPService validacaoCEPService,
                                              ValidacaoEmailService validacaoEmailService, ValidacaoisEmptyorNullService validacaoisEmptyorNullService,
                                              ValidacaoEnderecoService validacaoEnderecoService) {
        this.validacaoCNPJService = validacaoCNPJService;
        this.validacaoTelefoneService = validacaoTelefoneService;
        this.validacaoCEPService = validacaoCEPService;
        this.validacaoEmailService = validacaoEmailService;
        this.validacaoisEmptyorNullService = validacaoisEmptyorNullService;
        this.validacaoEnderecoService = validacaoEnderecoService;
    }

    public void validarCamposObrigatorios(EmpresaRequest empresaRequest) {
        if (empresaRequest == null) {
            throw new ValidacaoException("Os dados da empresa não podem estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getNomeFantasia())) {
            throw new ValidacaoException("O nome fantasia não pode estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getNomeSolicitante())) {
            throw new ValidacaoException("O nome do solicitante não pode estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getTelefoneSolicitante())) {
            throw new ValidacaoException("O telefone do solicitante não pode estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getRazaoSocial())) {
            throw new ValidacaoException("A razão social não pode estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getCnpj())) {
            throw new ValidacaoException("O CNPJ não pode estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getTelefoneEmpresas())) {
            throw new ValidacaoException("O telefone da empresa não pode estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getRamo())) {
            throw new ValidacaoException("O ramo não pode estar em branco");
        }
        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getPorteEmpresas())) {
            throw new ValidacaoException("O porte da empresa não pode estar em branco");
        }
        if (empresaRequest.getEndereco() == null || validacaoEnderecoService.enderecoEstaVazio(empresaRequest.getEndereco())) {
            throw new ValidacaoException("É necessário associar um endereço à empresa");
        }
        if (!validacaoEmailService.isValidEmail(empresaRequest.getEmail())) {
            throw new ValidacaoException("O e-mail inserido não é válido");
        }

        if (!validacaoCEPService.isValidCep(empresaRequest.getEndereco().getCep())) {
            throw new ValidacaoException("O CEP inserido não é válido");
        }

        if (!validacaoTelefoneService.isValidTelefone(empresaRequest.getTelefoneEmpresas())) {
            throw new ValidacaoException("O telefone da empresa não é válido");
        }
        if (!validacaoTelefoneService.isValidTelefone(empresaRequest.getTelefoneSolicitante())) {
            throw new ValidacaoException("O telefone do solicitante não é válido");
        }

        if (!validacaoisEmptyorNullService.isEmptyOrNull(empresaRequest.getCnpj())) {
            throw new ValidacaoException("O CNPJ não pode estar em branco");
        }
        if (!validacaoCNPJService.isValidCnpj(empresaRequest.getCnpj())) {
            throw new ValidacaoException("O CNPJ inserido não é válido");
        }
    }
}
