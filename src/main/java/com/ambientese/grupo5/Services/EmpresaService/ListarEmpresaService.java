package com.ambientese.grupo5.Services.EmpresaService;

import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListarEmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<EmpresaModel> getAllEmpresas() {
        return empresaRepository.findAll();
    }
}
