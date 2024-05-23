package com.ambientese.grupo5.Repository;

import com.ambientese.grupo5.Model.PerguntasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntasRepository extends JpaRepository<PerguntasModel, Long> {
<<<<<<< HEAD
    List<PerguntasModel> findByEixo(EixoEnum eixo);

=======
>>>>>>> develop-empresa-front
}
