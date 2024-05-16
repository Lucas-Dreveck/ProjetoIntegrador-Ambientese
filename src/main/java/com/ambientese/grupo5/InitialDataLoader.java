package com.ambientese.grupo5;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Model.EnderecoModel;
import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Model.Enums.PorteEnum;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import com.ambientese.grupo5.Repository.UsuarioRepository;
import com.github.javafaker.Faker;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private final Faker faker = new Faker(new Locale("pt-BR"));

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public void run(String... args) throws Exception {
        UsuarioModel rootUser = userRepository.findByLogin("root");

        if (rootUser == null) {
            // Popular tabela empresa
            int numberOfCompaniesToGenerate = 20;

            for (int i = 0; i < numberOfCompaniesToGenerate; i++) {
                EmpresaModel empresa = new EmpresaModel();
                empresa.setNomeFantasia(faker.company().name());
                empresa.setNomeSolicitante(faker.name().fullName());
                String cellPhone = faker.phoneNumber().cellPhone();
                if (cellPhone.length() > 15) {
                    cellPhone = cellPhone.substring(0, 15);
                }
                empresa.setTelefoneSolicitante(cellPhone);
                empresa.setRazaoSocial(faker.company().name());
                empresa.setCnpj(faker.number().digits(14));
                empresa.setInscricaoSocial(faker.number().digits(20));
                empresa.setEmail(faker.internet().emailAddress());
                String telefone = faker.phoneNumber().phoneNumber();
                if (telefone.length() > 15) {
                    telefone = telefone.substring(0, 15);
                }
                empresa.setTelefoneEmpresas(telefone);
                empresa.setRamo(faker.company().industry());
                empresa.setPorteEmpresas(getRandomPorte());

                EnderecoModel endereco = new EnderecoModel();
                endereco.setCep(faker.address().zipCode());
                endereco.setNumero(faker.number().numberBetween(1, 1000));
                endereco.setLogradouro(faker.address().streetName());
                endereco.setComplemento(faker.address().secondaryAddress());
                endereco.setCidade(faker.address().cityName());
                endereco.setBairro(faker.address().streetName());
                endereco.setUF(faker.address().stateAbbr());

                empresa.setEndereco(endereco);

                empresaRepository.save(empresa);
            }

            // Criação do usuário root
            UsuarioModel newUser = new UsuarioModel();
            newUser.setLogin("root");
            newUser.setPassword("root");
            newUser.setIsAdmin(true);

            userRepository.save(newUser);

            System.out.println("Banco pré preenchido com sucesso.");
            System.out.println("Usuário root criado com sucesso.");
            System.out.println("Login: root");
            System.out.println("Senha: root");
        } else {
            System.out.println("Usuário root já existe.");
            System.out.println("Login: root");
            System.out.println("Senha: root");
        }
    }

    private PorteEnum getRandomPorte() {
        PorteEnum[] portes = PorteEnum.values();
        int randomIndex = faker.random().nextInt(portes.length);
        return portes[randomIndex];
    }

}
