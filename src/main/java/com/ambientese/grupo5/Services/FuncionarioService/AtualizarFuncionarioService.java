package com.ambientese.grupo5.Services.FuncionarioService;

import com.ambientese.grupo5.DTO.FuncionarioRequest;
import com.ambientese.grupo5.Exception.ValidacaoException;
import com.ambientese.grupo5.Model.FuncionarioModel;
import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Repository.FuncionarioRepository;
import com.ambientese.grupo5.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarFuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public FuncionarioModel atualizarFuncionario(Long id, FuncionarioRequest funcionarioRequest) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Funcionário não encontrado com o ID: " + id));
        funcionarioModel.setNome(funcionarioRequest.getNome());
        funcionarioModel.setCpf(funcionarioRequest.getCpf());
        funcionarioModel.setEmail(funcionarioRequest.getEmail());
        funcionarioModel.setDataNascimento(funcionarioRequest.getDataNascimento());
        
        UsuarioModel usuarioModel = usuarioRepository.findById(funcionarioModel.getUsuario().getId())
                .orElseThrow(() -> new ValidacaoException("Funcionário não encontrado com o ID: " + id));;

        usuarioModel.setLogin(funcionarioRequest.getLogin());

        funcionarioModel.setUsuario(usuarioModel);
        return funcionarioRepository.save(funcionarioModel);
    }
}
