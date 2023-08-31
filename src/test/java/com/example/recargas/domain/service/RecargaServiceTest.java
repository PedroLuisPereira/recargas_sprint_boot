package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.HttpSaldo;
import com.example.recargas.domain.ports.PersonaRepository;
import com.example.recargas.domain.ports.RecargaRepository;
import com.example.recargas.infrastructure.output.persistence.PersonaPersistenceAdapter;
import com.example.recargas.infrastructure.output.persistence.RecargaPersistenceAdapter;
import com.example.recargas.infrastructure.output.persistence.entity.PersonaEntity;
import com.example.recargas.infrastructure.output.persistence.entity.RecargaEntity;
import com.example.recargas.infrastructure.output.persistence.mapper.PersonaMapper;
import com.example.recargas.infrastructure.output.persistence.mapper.RecargaMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RecargaServiceTest {

    @Mock
    PersonaRepository personaRepository;

    @Mock
    RecargaRepository recargaRepository;

    @Mock
    HttpSaldo httpSaldo;

    @InjectMocks
    RecargaService recargaService; //clase que va a recibir todos los mock


    @Test
    void debeListarTodosLosRegistros() {

        // 1. Preparación
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        List<Recarga> recargas = Arrays.asList(
          Recarga.getInstance(1L, 50000, "3006087877", "TIGO", persona),
          Recarga.getInstance(2L, 55000, "3006087877", "TIGO", persona)
        );

        Mockito.when(recargaRepository.list()).thenReturn(recargas);

        // 2. Ejecución
        List<Recarga> listar = recargaService.listar();


        // 3. Comparacion
        Assertions.assertEquals(2, listar.size());


    }

    /*
    @Test
    void debeCrearRecarga() {

        // 1. Preparación
        PersonaEntity personaEntity = new PersonaEntity(1L, "Juan", "juan@gmail.com");
        RecargaEntity recargaEntity = new RecargaEntity(
          null,
          2000,
          "3006087877",
          "TIGO",
          personaEntity
        );


        var personaRepository = Mockito.mock(com.example.recargas.infrastructure.output.persistence.repository.PersonaRepository.class); //simular
        Mockito.when(personaRepository.findById(1L)).thenReturn(Optional.of(personaEntity)); //cuando se llame

        var recargaRepository = Mockito.mock(com.example.recargas.infrastructure.output.persistence.repository.RecargaRepository.class); //simular
        Mockito.when(recargaRepository.save(recargaEntity)).thenReturn(recargaEntity); //cuando se llame

        var httpPuerto = Mockito.mock(HttpSaldo.class); //simular
        Mockito.when(httpPuerto.getSaldo()).thenReturn(new SaldoDto(100000)); //cuando se llame

        PersonaRepository personaPuerto = new PersonaPersistenceAdapter(personaRepository, new PersonaMapper());
        RecargaRepository recargaPuerto = new RecargaPersistenceAdapter(recargaRepository, new RecargaMapper(), new PersonaMapper());


        // 2. Ejecución
        var recargaService = new RecargaService(personaPuerto, recargaPuerto, httpPuerto);
        var recarga = recargaService.crear(new RecargaSolicitudCrear(
          2000,
          "3006087877",
          "TIGO",
          1L
        ));

        // 3. Comparacion
        Assertions.assertEquals(2000, recarga.getValor());
        Assertions.assertEquals("3006087877", recarga.getCelular());
        Assertions.assertEquals("TIGO", recarga.getOperador());

    }

    /*
    @Test
    @DisplayName("Se valida un celular incorrecto")
    void debeLanzarErrorCelularIncorrecto() {
        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          6000,
          "300608787711",
          "TIGO",
          1
        );

        CampoConException campoConException = Assertions.assertThrows(CampoConException.class,
          () -> recargaService.crear(recargaSolicitudCrear));

        Assertions.assertEquals("Celular incorrecto", campoConException.getMessage());
    }

    @Test
    @DisplayName("Se valida un operador incorrecto")
    void debeLanzarErrorOperadorIncorrecto() {
        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          6000,
          "3006087877",
          "OTRO",
          1
        );

        CampoConException campoConException = Assertions.assertThrows(CampoConException.class,
          () -> recargaService.crear(recargaSolicitudCrear));

        Assertions.assertEquals("Operador incorrecto", campoConException.getMessage());
    }

    @Test
    @DisplayName("Se valida una peronsa no existe")
    void debeLanzarErrorIdPersonaNoExiste() {
        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          6000,
          "3006087877",
          "TIGO",
          1
        );

        Mockito.when(personaPuerto.listarByid(recargaSolicitudCrear.getPersonaId())).thenReturn(Optional.empty());

        RegistroNotFoundException campoConException = Assertions.assertThrows(RegistroNotFoundException.class,
          () -> recargaService.crear(recargaSolicitudCrear));

        Assertions.assertEquals("No se encontro id de la persona", campoConException.getMessage());
        Mockito.verify(personaPuerto).listarByid(recargaSolicitudCrear.getPersonaId());
        Mockito.verify(personaPuerto, Mockito.times(1) ).listarByid(recargaSolicitudCrear.getPersonaId());
    }


     */
}
