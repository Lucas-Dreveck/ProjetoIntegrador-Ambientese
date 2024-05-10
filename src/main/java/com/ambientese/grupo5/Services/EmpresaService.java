package com.ambientese.grupo5.Services;

import com.ambientese.grupo5.DTO.EmpresaRequest;
import com.ambientese.grupo5.Model.EnderecoModel;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import com.ambientese.grupo5.Exception.ValidacaoException;
import com.ambientese.grupo5.Model.EmpresaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    private boolean isValidCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            return false;
        }
        int[] digitos = new int[cnpj.length()];
        for (int i = 0; i < cnpj.length(); i++) {
            digitos[i] = Character.getNumericValue(cnpj.charAt(i));
        }
        int soma = 0;
        soma += digitos[0] * 5;
        soma += digitos[1] * 4;
        soma += digitos[2] * 3;
        soma += digitos[3] * 2;
        soma += digitos[4] * 9;
        soma += digitos[5] * 8;
        soma += digitos[6] * 7;
        soma += digitos[7] * 6;
        soma += digitos[8] * 5;
        soma += digitos[9] * 4;
        soma += digitos[10] * 3;
        soma += digitos[11] * 2;
        int resto = soma % 11;
        int dv1 = resto < 2 ? 0 : 11 - resto;
        if (digitos[12] != dv1) {
            return false;
        }
        soma = 0;
        soma += digitos[0] * 6;
        soma += digitos[1] * 5;
        soma += digitos[2] * 4;
        soma += digitos[3] * 3;
        soma += digitos[4] * 2;
        soma += digitos[5] * 9;
        soma += digitos[6] * 8;
        soma += digitos[7] * 7;
        soma += digitos[8] * 6;
        soma += digitos[9] * 5;
        soma += digitos[10] * 4;
        soma += digitos[11] * 3;
        soma += digitos[12] * 2;
        resto = soma % 11;
        int dv2 = resto < 2 ? 0 : 11 - resto;
        return digitos[13] == dv2;    }

    private boolean isValidTelefone(String telefone) {
        // Verifica se o telefone é nulo ou se não corresponde ao padrão XXXXXXXXXXX
        return telefone == null || telefone.matches("\\d{10,11}");
    }



    private void validarCnpjUnico(String cnpj, Long empresaId) {
        Optional<EmpresaModel> empresaExistente = empresaRepository.findByCnpj(cnpj);
        empresaExistente.ifPresent(empresa -> {
            if (!empresa.getId().equals(empresaId)) {
                throw new ValidacaoException("Já existe uma empresa cadastrada com este CNPJ");
            }
        });
    }


    private boolean isValidCep(String cep) {
        String cepRegex = "\\d{8}";
        return cep != null && cep.matches(cepRegex);
        }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }
   private boolean enderecoEstaVazio(EnderecoModel endereco) {
        return isEmptyOrNull(String.valueOf(endereco.getLogradouro())) &&
                isEmptyOrNull(endereco.getNumero() == null ? null : String.valueOf(endereco.getNumero())) &&
                isEmptyOrNull(endereco.getBairro()) &&
                isEmptyOrNull(endereco.getCidade()) &&
                isEmptyOrNull(endereco.getUF()) &&
                isEmptyOrNull(endereco.getCep());
    }

    private void validarCamposObrigatorios(EmpresaRequest empresaRequest) {
        if (empresaRequest == null) {
            throw new ValidacaoException("Os dados da empresa não podem estar em branco");
        }
        if (isEmptyOrNull(empresaRequest.getNomeFantasia())) {
            throw new ValidacaoException("O nome fantasia não pode estar em branco");
        }
        if (isEmptyOrNull(empresaRequest.getNomeSolicitante())) {
            throw new ValidacaoException("O nome do solicitante não pode estar em branco");
        }
        if (isEmptyOrNull(empresaRequest.getTelefoneSolicitante())) {
            throw new ValidacaoException("O telefone do solicitante não pode estar em branco");
        }
        if (isEmptyOrNull(empresaRequest.getRazaoSocial())) {
            throw new ValidacaoException("A razão social não pode estar em branco");
        }
        if (isEmptyOrNull(empresaRequest.getCnpj())) {
            throw new ValidacaoException("O CNPJ não pode estar em branco");
        }
        if (!isValidCnpj(empresaRequest.getCnpj())) {
            throw new ValidacaoException("O CNPJ inserido não é válido");
        }
        if (isEmptyOrNull(empresaRequest.getTelefoneEmpresas())) {
            throw new ValidacaoException("O telefone da empresa não pode estar em branco");
        }
        if (isEmptyOrNull(empresaRequest.getRamo())) {
            throw new ValidacaoException("O ramo não pode estar em branco");
        }
        if (empresaRequest.getPorteEmpresas() == null) {
            throw new ValidacaoException("O porte da empresa não pode estar em branco");
        }
        if (empresaRequest.getEndereco() == null || enderecoEstaVazio(empresaRequest.getEndereco())) {
            throw new ValidacaoException("É necessário associar um endereço à empresa");
        }
        if (!isValidEmail(empresaRequest.getEmail())) {
            throw new ValidacaoException("O e-mail inserido não é válido");
        }

        if (!isValidCep(empresaRequest.getEndereco().getCep())) {
            throw new ValidacaoException("O CEP inserido não é válido");
        }

        if (isValidTelefone(empresaRequest.getTelefoneEmpresas())) {
            throw new ValidacaoException("O telefone da empresa não é válido");
        }
        if (isValidTelefone(empresaRequest.getTelefoneSolicitante())) {
            throw new ValidacaoException("O telefone da empresa não é válido");
        }
    }
    private void mapearEmpresa(EmpresaModel empresaModel, EmpresaRequest empresaRequest) {
        empresaModel.setNomeFantasia(empresaRequest.getNomeFantasia());
        empresaModel.setNomeSolicitante(empresaRequest.getNomeSolicitante());
        empresaModel.setTelefoneSolicitante(empresaRequest.getTelefoneSolicitante());
        empresaModel.setRazaoSocial(empresaRequest.getRazaoSocial());
        empresaModel.setCnpj(empresaRequest.getCnpj());
        empresaModel.setInscricaoSocial(empresaRequest.getInscricaoSocial());
        empresaModel.setEmail(empresaRequest.getEmail());
        empresaModel.setTelefoneEmpresas(empresaRequest.getTelefoneEmpresas());
        empresaModel.setRamo(empresaRequest.getRamo());
        empresaModel.setPorteEmpresas(empresaRequest.getPorteEmpresas());
        empresaModel.setEndereco(empresaRequest.getEndereco());
    }
    public List<EmpresaModel> getAllEmpresas() {
        return empresaRepository.findAll();
    }
    public EmpresaModel criarEmpresa(EmpresaRequest empresaRequest) {
        validarCamposObrigatorios(empresaRequest);
        validarCnpjUnico(empresaRequest.getCnpj(), null); // Passa null como ID, pois é uma nova empresa
        EmpresaModel empresaModel = new EmpresaModel(); // Criar novo objeto EmpresaModel
        mapearEmpresa(empresaModel, empresaRequest); // Passar o objeto para o método mapearEmpresa
        return empresaRepository.save(empresaModel);
    }
    public EmpresaModel atualizarEmpresa(Long id, EmpresaRequest empresaRequest) {
        EmpresaModel empresaModel = empresaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Empresa não encontrada com o ID: " + id));
        validarCamposObrigatorios(empresaRequest);
        validarCnpjUnico(empresaRequest.getCnpj(), id); // Passa o ID da empresa sendo atualizada
        mapearEmpresa(empresaModel, empresaRequest);
        return empresaRepository.save(empresaModel);
    }
    public void deleteEmpresa(Long id) {
        empresaRepository.findById(id).ifPresent(empresaRepository::delete);
    }
}
