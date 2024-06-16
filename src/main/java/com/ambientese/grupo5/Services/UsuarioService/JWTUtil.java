package com.ambientese.grupo5.Services.UsuarioService;

import com.ambientese.grupo5.Config.JWTConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import jakarta.annotation.PostConstruct;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class JWTUtil {
    private static String SECRET;
    private static Algorithm algorithm;
    private static JWTVerifier verifier;

    @Autowired
    private JWTConfig jwtConfig;

    @PostConstruct
    public void init() {
        SECRET = jwtConfig.getJwtSecret();
        algorithm = Algorithm.HMAC256(SECRET);
        verifier = JWT.require(algorithm).build();
    }

    public static String generateToken(String login) {
        return JWT.create()
                .withSubject(login)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .sign(algorithm);
    }

    public static String validateToken(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
