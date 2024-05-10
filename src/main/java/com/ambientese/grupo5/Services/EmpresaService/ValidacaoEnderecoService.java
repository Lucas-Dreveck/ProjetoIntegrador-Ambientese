package com.ambientese.grupo5.Services.EmpresaService;

import com.ambientese.grupo5.Model.EnderecoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoEnderecoService {
    private final ValidacaoisEmptyorNullService validacaoisEmptyorNullService;

    @Autowired
    public ValidacaoEnderecoService(ValidacaoisEmptyorNullService validacaoisEmptyorNullService) {
        this.validacaoisEmptyorNullService = validacaoisEmptyorNullService;
    }

    public boolean enderecoEstaVazio(EnderecoModel endereco) {
        return  validacaoisEmptyorNullService.isEmptyOrNull(String.valueOf(endereco.getLogradouro())) &&
                validacaoisEmptyorNullService.isEmptyOrNull(endereco.getNumero() == null ? null : String.valueOf(endereco.getNumero())) &&
                validacaoisEmptyorNullService.isEmptyOrNull(endereco.getBairro()) &&
                validacaoisEmptyorNullService.isEmptyOrNull(endereco.getCidade()) &&
                validacaoisEmptyorNullService.isEmptyOrNull(endereco.getUF()) &&
                validacaoisEmptyorNullService.isEmptyOrNull(endereco.getCep());
    }
}
