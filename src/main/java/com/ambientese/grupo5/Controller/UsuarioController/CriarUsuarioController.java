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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioModel usuarioModel) {
        String username = usuarioModel.getUsername();
        String password = usuarioModel.getPassword();


        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome de usuário e senha são obrigatórios!");
        }

        if ("usuario_correto".equals(username) && "senha_correta".equals(password)) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválido!");
        }
    }
}
