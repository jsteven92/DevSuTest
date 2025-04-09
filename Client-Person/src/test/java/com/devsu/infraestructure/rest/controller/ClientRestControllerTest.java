package com.devsu.infraestructure.rest.controller;

import static org.mockito.Mockito.when;

import java.io.ObjectInputFilter.Status;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.devsu.domain.model.Client;
import com.devsu.domain.model.Person;
import com.devsu.domain.model.enums.Gender;
import com.devsu.usecase.IClientInteractor;

@WebMvcTest
class ClientRestControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientInteractor clientInteractor;

    @Test
    @DisplayName("GET /api/clientes/{id} - Success")
    void testGetClientByIdSuccess() throws Exception {
        Client client = createClient();

        when(clientInteractor.findClientsByClientId(1L)).thenReturn(Optional.of(client));
       
        
        mockMvc.perform(get("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status() .isOk())
                .andExpect(jsonPath("$.clientId").value(1L))
                .andExpect(jsonPath("$.person.name").value("name test"))
                .andExpect(jsonPath("$.state").value(true));
    }

    @Test
    @DisplayName("GET /api/clientes/{id} - Not Found")
    void testGetClientByIdNotFound() throws Exception {
        when(clientInteractor.findClientsByClientId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clientes/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Client not found"));
    }
    
    public Client createClient() {
		return new Client(1L, "1234", true, new Person(1L, "123456", "name test", Gender.MASCULINO, 20, "av 25", "1234567899"));
	}

}


