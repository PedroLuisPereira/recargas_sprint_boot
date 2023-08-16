package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.exception.CampoConException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.ports.PersonaPuerto;
import com.example.recargas.domain.ports.RecargaPuerto;
import com.example.recargas.domain.service.RecargaService;
import com.example.recargas.infrastructure.output.persistence.entity.PersonaEntity;
import com.example.recargas.infrastructure.output.persistence.entity.RecargaEntity;
import com.example.recargas.infrastructure.output.persistence.repository.PersonaRepository;
import com.example.recargas.infrastructure.output.persistence.repository.RecargaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class RecargaServiceTest {

    @InjectMocks
    RecargaService recargaService; //clase que va a recibir todos los mock

    @Mock
    RecargaRepository recargaRepository; //bean que se va a mockiar

    @Test
    void debeListarTodosLosRegistros() {

//        // 1. Preparación
//        PersonaEntity personaEntity = new PersonaEntity(1L,"Juan", "juan@gmail.com");
//
//        List<RecargaEntity> recargaEntities = new ArrayList<>(Arrays.asList(
//          new RecargaEntity(1L, 50000, "3006087877", "TIGO", personaEntity),
//          new RecargaEntity(2L, 30000, "3106087877", "CLARO", personaEntity)));
//
//        var recargaRepository = Mockito.mock(RecargaRepository.class); //simular repositorio
//        var personaRepository = Mockito.mock(PersonaRepository.class); //simular repositorio
//        Mockito.when(recargaRepository.findAll()).thenReturn(recargaEntities);
//
//        // 2. Ejecución
//        var  ServicioPersonaListar = new RecargaService(personaRepository,recargaRepository,) ServicioPersonaListar(repositorioPersona);
//        var personaListar = ServicioPersonaListar.listar();
//
//        // 3. Comparacion
//        Assertions.assertEquals(2, personaListar.size());
//
//        List<RecargaEntity>
//        when(recargaRepository.findAll()).thenReturn(Collections.emptyList());
//
//        assertThat(countryService.findAll()).isEmpty();
//
//        List<Persona> personas = personaService.listar();
//        assertEquals(3, personas.size());
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
