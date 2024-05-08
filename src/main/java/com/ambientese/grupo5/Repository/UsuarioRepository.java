package com.Ambientese.Empresa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Ambientese.Empresa.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

}
