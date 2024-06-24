import com.ambientese.grupo5.Model.FuncionarioModel;
import com.ambientese.grupo5.Services.FuncionarioService.BuscarFuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BuscarFuncionarioPorIdTeste {

    private MockMvc mockMvc;

    @Mock
    private BuscarFuncionarioService buscarFuncionarioService;

    @InjectMocks
    private BuscarFuncionarioController buscarFuncionarioController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(buscarFuncionarioController).build();
    }

    @Test
    public void testBuscarFuncionarioPorId() throws Exception {
        // Mock objeto de resposta
        FuncionarioModel funcionarioEncontrado = new FuncionarioModel();
        funcionarioEncontrado.setId(1L);
        funcionarioEncontrado.setNome("Funcionário Encontrado");
        funcionarioEncontrado.setCargo("Analista");

        // Mock comportamento do serviço
        when(buscarFuncionarioService.buscarFuncionarioPorId(1L))
                .thenReturn(funcionarioEncontrado);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/Funcionario/Get/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Funcionário Encontrado"))
                .andExpect(jsonPath("$.cargo").value("Analista"))
                .andReturn();
    }
}
