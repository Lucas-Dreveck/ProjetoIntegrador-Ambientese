package com.ambientese.grupo5.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Services.UsuarioService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
        List<UsuarioModel> usuarioModels = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarioModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioModel> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> createUsuario(@Valid @RequestBody UsuarioModel usuarioModel) {
        UsuarioModel createdUsuarioModel = usuarioService.createUsuario(usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuarioModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioModel usuarioModel) {
        Optional<UsuarioModel> updatedUsuario = usuarioService.updateUsuario(id, usuarioModel);
        return updatedUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
        }
    }
}