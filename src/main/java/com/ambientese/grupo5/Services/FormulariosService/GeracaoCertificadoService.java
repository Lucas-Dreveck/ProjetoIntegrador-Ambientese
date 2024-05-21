package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import org.springframework.stereotype.Service;

@Service
public class GeracaoCertificadoService {

    public NivelCertificadoEnum calcularNivelCertificado(int pontuacaoFinal) {
        if (pontuacaoFinal >= 100) {
            return NivelCertificadoEnum.Ouro;
        } else if (pontuacaoFinal >= 75.1) {
            return NivelCertificadoEnum.Prata;
        } else {
            return NivelCertificadoEnum.Bronze;
        }
    }
}
