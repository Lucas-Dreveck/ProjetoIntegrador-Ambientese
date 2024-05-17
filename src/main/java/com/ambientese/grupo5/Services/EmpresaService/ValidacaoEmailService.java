package com.ambientese.grupo5.Services.EmpresaService;

import org.springframework.stereotype.Service;

@Service
public class ValidacaoEmailService {
    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }
}
