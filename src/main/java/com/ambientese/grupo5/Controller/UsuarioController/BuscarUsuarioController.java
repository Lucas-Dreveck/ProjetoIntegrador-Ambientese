package com.ambientese.grupo5.Controller.UsuarioController;

import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Services.UsuarioService.ListarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Usuarios")
public class BuscarUsuarioController {

    private final ListarUsuarioService usuarioService;

    @Autowired
    public BuscarUsuarioController(ListarUsuarioService usuarioService) {

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

}
