package com.ambientese.grupo5.Services.EmpresaService;

import org.springframework.stereotype.Service;

@Service
public class ValidacaoisEmptyorNullService {
    public boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }
}
