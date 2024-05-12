package com.ambientese.grupo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Repository.UsuarioRepository;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        UsuarioModel rootUser = userRepository.findByLogin("root");

        if (rootUser == null) {
            UsuarioModel newUser = new UsuarioModel();
            newUser.setLogin("root");
            newUser.setPassword("root");
            newUser.setIsAdmin(true);

            userRepository.save(newUser);
            System.out.println("Usuário root criado com sucesso.");
        } else {
            System.out.println("Usuário root já existe.");
        }
    }
}
