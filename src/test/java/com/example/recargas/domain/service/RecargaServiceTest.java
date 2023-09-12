package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.exception.NoSaldoException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.RecargaHttpSaldo;
import com.example.recargas.domain.ports.PersonaRepository;
import com.example.recargas.domain.ports.RecargaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class RecargaServiceTest {

    @Mock
    PersonaRepository personaRepository;

    @Mock
    RecargaRepository recargaRepository;

    @Mock
    RecargaHttpSaldo httpSaldo;

    @InjectMocks
    RecargaService recargaService; //clase que va a recibir todos los mock


    @Test
    void debeListarTodosLasRecargas() {

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

    @Test
    void debeCrearUnaNuevaRecarga() {

        // 1. Preparación
        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          50000,
          "3006087877",
          "TIGO",
          1L
        );

        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");
        Recarga recarga = Recarga.getInstance(
          50000,
          "3006087877",
          "TIGO",
          persona
        );

        Mockito.when(personaRepository.listarByid(anyLong())).thenReturn(Optional.of(persona));
        Mockito.when(httpSaldo.getSaldo(anyString())).thenReturn(new SaldoDto(500000));
        Mockito.when(recargaRepository.save(any())).thenReturn(recarga);

        // 2. Ejecución
        Recarga respuesta = recargaService.crear(recargaSolicitudCrear);

        // 3. Comparacion
        Assertions.assertEquals(50000, respuesta.getValor());

    }

    @Test
    void debeLanzarErrorPersonaNoExite() {

        // 1. Preparación
        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          50000,
          "3006087877",
          "TIGO",
          1L
        );

        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");
        Recarga recarga = Recarga.getInstance(
          50000,
          "3006087877",
          "TIGO",
          persona
        );

        Mockito.when(personaRepository.listarByid(anyLong())).thenReturn(Optional.empty());
        Mockito.when(httpSaldo.getSaldo(anyString())).thenReturn(new SaldoDto(500000));
        Mockito.when(recargaRepository.save(any())).thenReturn(recarga);

        // 2. Ejecución
        RegistroNotFoundException thrown = Assertions.assertThrows(RegistroNotFoundException.class, () -> {
            recargaService.crear(recargaSolicitudCrear);
        });

        // 3. Comparacion
        Assertions.assertEquals("No se encontro id de la persona", thrown.getMessage());

    }

    @Test
    void debeLanzarErrorSinSaldo() {

        // 1. Preparación
        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          50000,
          "3006087877",
          "TIGO",
          1L
        );

        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");
        Recarga recarga = Recarga.getInstance(
          50000,
          "3006087877",
          "TIGO",
          persona
        );

        Mockito.when(personaRepository.listarByid(anyLong())).thenReturn(Optional.of(persona));
        Mockito.when(httpSaldo.getSaldo(anyString())).thenReturn(new SaldoDto(5));
        Mockito.when(recargaRepository.save(any())).thenReturn(recarga);

        // 2. Ejecución
        NoSaldoException thrown = Assertions.assertThrows(NoSaldoException.class, () -> {
            recargaService.crear(recargaSolicitudCrear);
        });

        // 3. Comparacion
        Assertions.assertEquals("No se puede hacer la recarga saldo insuficiente", thrown.getMessage());

    }


}
