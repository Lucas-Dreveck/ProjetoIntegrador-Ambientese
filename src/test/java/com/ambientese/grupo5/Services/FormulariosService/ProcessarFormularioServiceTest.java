package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.DTO.FormularioRequest;
import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Repository.FormularioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProcessarFormularioServiceTest {

    @Mock
    private FormularioRepository formularioRepository;

    @InjectMocks
    private ProcessarFormularioService processarFormularioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarProcessarEGerarCertificado() {
        List<FormularioRequest> formularioRequestList = Arrays.asList(
                new FormularioRequest(EixoEnum.Social, RespostasEnum.Conforme),
                new FormularioRequest(EixoEnum.Ambiental, RespostasEnum.Conforme),
                new FormularioRequest(EixoEnum.Governamental, RespostasEnum.NãoConforme)
        );

        FormularioModel expectedFormularioModel = getFormularioModel();

        when(formularioRepository.save(any())).thenReturn(expectedFormularioModel);

        FormularioModel actualFormularioModel = processarFormularioService.criarProcessarEGerarCertificado(formularioRequestList);

        assertEquals(expectedFormularioModel, actualFormularioModel);
        verify(formularioRepository, times(1)).save(any());
    }

    private static FormularioModel getFormularioModel() {
        FormularioModel expectedFormularioModel = new FormularioModel();
        expectedFormularioModel.setPontuacaoFinal(66);
        expectedFormularioModel.setPontuacaoSocial(1);
        expectedFormularioModel.setPontuacaoAmbiental(1);
        expectedFormularioModel.setPontuacaoGovernamental(0);
        expectedFormularioModel.setCertificado(NivelCertificadoEnum.Bronze);
        expectedFormularioModel.setRespostas(Arrays.asList(RespostasEnum.Conforme, RespostasEnum.Conforme, RespostasEnum.NãoConforme));
        return expectedFormularioModel;
    }

    @Test
    void testCalcularNivelCertificadoOuro() {
        NivelCertificadoEnum actualNivel = processarFormularioService.calcularNivelCertificado(100);
        assertEquals(NivelCertificadoEnum.Ouro, actualNivel);
    }

    @Test
    void testCalcularNivelCertificadoPrata() {
        NivelCertificadoEnum actualNivel = processarFormularioService.calcularNivelCertificado(80);
        assertEquals(NivelCertificadoEnum.Prata, actualNivel);
    }

    @Test
    void testCalcularNivelCertificadoBronze() {
        NivelCertificadoEnum actualNivel = processarFormularioService.calcularNivelCertificado(60);
        assertEquals(NivelCertificadoEnum.Bronze, actualNivel);
    }
}
