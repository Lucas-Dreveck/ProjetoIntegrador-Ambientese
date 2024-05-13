package com.ambientese.grupo5.Controller.UsuarioController;

import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Services.UsuarioService.CriarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/Usuarios")
public class CriarUsuarioController {

    private final CriarUsuarioService usuarioService;

    @Autowired
    public CriarUsuarioController(CriarUsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> createUsuario(@Valid @RequestBody UsuarioModel usuarioModel) {
        UsuarioModel createdUsuarioModel = usuarioService.createUsuario(usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuarioModel);
    }
}