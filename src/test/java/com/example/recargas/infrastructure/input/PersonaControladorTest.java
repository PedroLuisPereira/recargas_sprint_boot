package com.example.recargas.infrastructure.input;

import com.example.recargas.application.persona.dto.PersonaCrearDto;
import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.RecargaRepository;
import com.example.recargas.domain.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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

    @Autowired
    RecargaRepository recargaRepository;

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
    }

    @Test
    void debeListarUnaPersona() throws Exception {

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
    void noDebeListarUnaPersona() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/personas/1000").contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNotFound())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message", is("No se encontro persona con ese Id")));
        ;
    }

    @Test
    void debeCrearUnaPersona() throws Exception {

        PersonaCrearDto personaCrearDto = new PersonaCrearDto("Ana", "ana@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/personas")
            .content(asJsonString(personaCrearDto))
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
    void debeActualizarUnaPersona() throws Exception {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Ana Maria", "ana1@gmail.com");
        Persona persona = personaService.crear(personaSolicitudCrear);

        PersonaCrearDto personaCrearDto = new PersonaCrearDto("Ana Maria up", "ana1@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/personas/" + persona.getId())
            .content(asJsonString(personaCrearDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.nombre", is("Ana Maria up")));
    }

    @Test
    void noDebeActualizarUnaPersonaNoExiste() throws Exception {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Ana Maria", "ana1@gmail.com");
        Persona persona = personaService.crear(personaSolicitudCrear);

        PersonaCrearDto personaCrearDto = new PersonaCrearDto("Ana Maria", "ana1@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/personas/10000")
            .content(asJsonString(personaCrearDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNotFound())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message", is("No se encontro la persona ese Id")));
    }

    @Test
    void noDebeActualizarUnaPersonaEmailExiste() throws Exception {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Ana Maria", "ana1@gmail.com");
        Persona persona = personaService.crear(personaSolicitudCrear);

        personaSolicitudCrear = new PersonaSolicitudCrear("Daniel", "daniel@gmail.com");
        personaService.crear(personaSolicitudCrear);

        PersonaCrearDto personaCrearDto = new PersonaCrearDto("Ana Maria", "daniel@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/personas/" + persona.getId())
            .content(asJsonString(personaCrearDto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isBadRequest())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message", is("Ya existe una persona con ese email")));
    }

    @Test
    void debeEliminarUnaPersona() throws Exception {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("maria", "ana1@gmail.com");
        Persona persona = personaService.crear(personaSolicitudCrear);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/personas/" + persona.getId())
            .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNoContent())
        ;
    }

    @Test
    void noDebeEliminarUnaPersonaNoExiste() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/personas/10000")
            .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isNotFound())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message", is("No se encontro persona con ese Id")));
    }

    @Test
    @Disabled
    void noDebeEliminarUnaPersonaTieneRecargas() throws Exception {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("maria", "ana1@gmail.com");
        Persona persona = personaService.crear(personaSolicitudCrear);

        Recarga recarga = Recarga.getInstance(20000, "3006087877", "TIGO", persona);
        recargaRepository.save(recarga);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/personas/" + persona.getId())
            .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers
            .status()
            .isBadRequest())
          .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message", is("No se puede eliminar registro")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
