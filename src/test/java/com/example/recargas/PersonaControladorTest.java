package com.example.recargas;

import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
class PersonaControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PersonaService personaService;

    @Test
    void debeListarTodasLasPersonas() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/personas").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].nombre", is("Juan")));
        ;
    }

    @Test
    void debeListarUnaPersonas() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/personas/1").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.nombre", is("Juan")));
        ;
    }

    @Test
    void noDebeListarUnaPersonas() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/personas/1000").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNotFound())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message", is("No se encontro persona con Id: 1000")));
        ;
    }

    @Test
    void debeCrearUnaPersonas() throws Exception {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Ana", "ana@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/personas")
            .content(asJsonString(personaSolicitudCrear))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isCreated())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.nombre", is("Ana")));
        ;
    }

    @Test
    void debeEliminarUnaPersonas() throws Exception {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("maria", "ana1@gmail.com");
        Persona persona = personaService.crear(personaSolicitudCrear);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/personas/" + persona.getId())
            .content(asJsonString(personaSolicitudCrear))
            .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNoContent())
        ;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}