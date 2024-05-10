package edu.comillas.icai.gitt.pat.spring.jpa.controller;


import edu.comillas.icai.gitt.pat.spring.jpa.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.jpa.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.jpa.service.ServicioClases;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ControladorClases.class)
public class ControladorClasesIntegrationTest {

    private static final String NAME = "Name";
    private static final String EMAIL = "name@email.com";
    private static final Integer TARIFA = 10;
    private static final Integer PASS = 10;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioClases servicioClases;

    @Test
    void registerOk() throws Exception {
        // Given ...
        Mockito.when(servicioClases.perfilCrear(Mockito.any(RegisterRequest.class)))
                .thenReturn(new ProfileResponse(NAME, EMAIL, TARIFA));
        String request = "{" +
                "\"nombre\":\"" + NAME + "\"," +
                "\"email\":\"" + EMAIL + "\"," +
                "\"tarifa\":\"" + TARIFA + "\"," +
                "\"contrasena\":\"aaaaaaA1\"}";
        // When ...
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                // Then ...
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("{" +
                        "\"nombre\":\"" + NAME + "\"," +
                        "\"email\":\"" + EMAIL + "\"," +
                        "\"tarifa\":\"" + TARIFA + "\"}"));
    }

    @Test void registerInvalidPassword() throws Exception {
        // Given ...
        String requestBody = "{" +
                "\"name\":\"" + NAME + "\"," +
                "\"email\":\"" + EMAIL + "\"," +
                "\"tarifa\":\"" + TARIFA + "\"," +
                "\"password\":\"holamundo\"}";  //Contraseña que no cumple los requisitos

        // When ...
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                // Then ...
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }





}
