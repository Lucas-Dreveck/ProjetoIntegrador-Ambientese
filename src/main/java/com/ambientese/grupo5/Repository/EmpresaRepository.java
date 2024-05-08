    package com.Ambientese.Empresa.Repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import com.Ambientese.Empresa.Model.EmpresaModel;

    public interface EmpresaRepository extends JpaRepository<EmpresaModel, Long> {
        boolean existsByCnpj(String cnpj);
    }
