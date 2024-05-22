package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.EmpresaModel;
import com.ambientese.grupo5.Model.Enums.NivelCertificadoEnum;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Repository.EmpresaRepository;
import com.ambientese.grupo5.Repository.FormularioRepository;
import com.ambientese.grupo5.DTO.FormularioRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProcessarFormularioServiceTest {

    @Mock
    private FormularioRepository formularioRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private ProcessarFormularioService processarFormularioService;

    @Test
    public void testCriarProcessarEGerarCertificado_Success() {
        // Mock data
        Long empresa_id = 1L;
        List<FormularioRequest> formularioRequestList = new ArrayList<>();
        // Adicione alguns objetos FormularioRequest ao formularioRequestList
        FormularioRequest formularioRequest1 = new FormularioRequest();
        formularioRequest1.setRespostaUsuario(RespostasEnum.Conforme);
        // Adicione outros objetos FormularioRequest ao formularioRequestList
        formularioRequestList.add(formularioRequest1);
        // Configure mocks
        EmpresaModel empresa = new EmpresaModel();
        when(empresaRepository.findById(empresa_id)).thenReturn(Optional.of(empresa));
        // Execute the method
        FormularioModel result = processarFormularioService.criarProcessarEGerarCertificado(empresa_id, formularioRequestList);
        // Verify the result
        assertEquals(RespostasEnum.Conforme, result.getRespostas().get(0));
        assertEquals(NivelCertificadoEnum.Bronze, result.getCertificado());
        verify(formularioRepository, times(1)).save(result);
    }

    @Test
    public void testCriarProcessarEGerarCertificado_EmpresaNotFound() {
        // Mock data
        Long empresaId = 1L;
        List<FormularioRequest> formularioRequestList = new ArrayList<>();
        // Configure mocks
        when(empresaRepository.findById(empresaId)).thenReturn(Optional.empty());
        // Execute and verify
        assertThrows(RuntimeException.class, () -> processarFormularioService.criarProcessarEGerarCertificado(empresaId, formularioRequestList));
        verify(formularioRepository, never()).save(any());
    }
}
