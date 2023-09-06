package com.example.recargas.infrastructure.input;

import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.ports.HttpSaldo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
class RecargaControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  HttpSaldo httpSaldo;

  @Test
  void debeListarTodasLasRecargas() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/api/recargas").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers
            .status()
            .isOk())
        .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].celular", is("3001234567")))
        .andExpect(jsonPath("$.length()").value(3));
  }

  @Test
  void debeCrearUnaRecarga() throws Exception {

    // simular un servicio externo
    Mockito.when(httpSaldo.getSaldo()).thenReturn(new SaldoDto(100000));

    RecargaCrearDto recargaCrearDto = new RecargaCrearDto(20000, "3001234567", "TIGO", 1L);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/recargas")
        .content(asJsonString(recargaCrearDto))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers
            .status()
            .isCreated())
        .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.celular", is("3001234567")));

  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
