package com.ambientese.grupo5.Services.UsuarioService;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository userRepository;

    public boolean authenticate(String login, String password) {
        UsuarioModel user = userRepository.findByLogin(login);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public void register(String login, String plainTextPassword) {
        String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        UsuarioModel newUser = new UsuarioModel();
        newUser.setLogin(login);
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser);
    }

    public String login(String login, String plainTextPassword) {
        if (authenticate(login, plainTextPassword)) {
            return JWTUtil.generateToken(login);
        }
        return null;
    }

    public String validateToken(String token) {
        return JWTUtil.validateToken(token);
    }
}
