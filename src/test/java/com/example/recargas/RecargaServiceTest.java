package com.example.recargas;

import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.exception.CampoConException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.ports.PersonaPuerto;
import com.example.recargas.domain.service.RecargaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
class RecargaServiceTest {


    @InjectMocks
    RecargaService recargaService; //clase que va a recibir todos los mock

    @Mock
    PersonaPuerto personaPuerto; //bean que se va a mockiar

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
}
