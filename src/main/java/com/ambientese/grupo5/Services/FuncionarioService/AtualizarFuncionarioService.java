package com.ambientese.grupo5.Services.FuncionarioService;

import com.ambientese.grupo5.DTO.FuncionarioRequest;
import com.ambientese.grupo5.Exception.ValidacaoException;
import com.ambientese.grupo5.Model.FuncionarioModel;
import com.ambientese.grupo5.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarFuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioModel atualizarFuncionario(Long id, FuncionarioRequest funcionarioRequest) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Funcionário não encontrado com o ID: " + id));
        return funcionarioRepository.save(funcionarioModel);
    }
}
