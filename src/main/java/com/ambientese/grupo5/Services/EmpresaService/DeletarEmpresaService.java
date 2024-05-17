package com.ambientese.grupo5.Services.EmpresaService;

import com.ambientese.grupo5.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarEmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public DeletarEmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;

    }
    public void deleteEmpresa(Long id) {
        empresaRepository.findById(id).ifPresent(empresaRepository::delete);
    }
}
