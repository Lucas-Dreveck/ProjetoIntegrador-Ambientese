package com.ambientese.grupo5.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ambientese.grupo5.Model.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long>{
    
}
