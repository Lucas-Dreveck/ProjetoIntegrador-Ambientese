package com.ambientese.grupo5.Services.EmpresaService;

import com.ambientese.grupo5.Model.Enums.PorteEnum;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoisEmptyorNullService {
    public boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }
    public boolean isEmptyOrNull(PorteEnum porte) {
        return porte == null;
    }
}
