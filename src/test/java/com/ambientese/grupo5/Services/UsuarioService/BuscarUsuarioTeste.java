import com.ambientese.grupo5.Model.UsuarioModel;
import com.ambientese.grupo5.Services.UsuarioService.BuscarUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BuscarUsuarioTeste {

    private MockMvc mockMvc;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @InjectMocks
    private BuscarUsuarioController buscarUsuarioController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(buscarUsuarioController).build();
    }

    @Test
    public void testBuscarUsuarioPorId() throws Exception {
        // Mock objeto de resposta
        UsuarioModel usuarioEncontrado = new UsuarioModel();
        usuarioEncontrado.setId(1L);
        usuarioEncontrado.setNome("Usuário Encontrado");
        usuarioEncontrado.setEmail("usuario.encontrado@teste.com");

        // Mock comportamento do serviço
        when(buscarUsuarioService.buscarUsuarioPorId(1L))
                .thenReturn(usuarioEncontrado);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/Usuario/Get/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Usuário Encontrado"))
                .andExpect(jsonPath("$.email").value("usuario.encontrado@teste.com"))
                .andReturn();
    }
}
