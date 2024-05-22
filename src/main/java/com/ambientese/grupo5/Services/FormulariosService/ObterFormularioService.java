package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObterFormularioService {

    private final FormularioRepository formularioRepository;

    @Autowired
    public ObterFormularioService(FormularioRepository formularioRepository) {
        this.formularioRepository = formularioRepository;
    }

    public FormularioModel obterFormularioPorId(long id) {
        return formularioRepository.findById(id).orElseThrow(() -> new RuntimeException("Formulário não encontrado"));
    }

    public void salvarFormulario(FormularioModel formulario) {
        formularioRepository.save(formulario);
    }
}
