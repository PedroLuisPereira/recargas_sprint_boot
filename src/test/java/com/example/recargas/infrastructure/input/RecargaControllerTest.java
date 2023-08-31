package com.example.recargas.infrastructure.input;

import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.service.RecargaService;
import com.example.recargas.infrastructure.output.persistence.PersonaPersistenceAdapter;
import com.example.recargas.infrastructure.output.persistence.RecargaPersistenceAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;


import java.util.Optional;

import static org.mockito.Mockito.when;


@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
class RecargaControllerTest {

    @MockBean
    PersonaPersistenceAdapter personaPersistenceAdapter;

    @MockBean
    RecargaPersistenceAdapter recargaPersistenceAdapter;

    @Autowired
    RecargaService recargaService;


    /**
     * Simulando beans
     */
    @Test
    void debeRetornarErrorPersonaNoExiste() {

        when(personaPersistenceAdapter.listarByid(1L)).thenReturn(Optional.empty());

        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          1,
          "3006087877",
          "TIGO",
          1L
        );

        RegistroNotFoundException thrown = Assertions.assertThrows(RegistroNotFoundException.class, () -> {
            recargaService.crear(recargaSolicitudCrear);
        });

        Assertions.assertEquals("No se encontro id de la persona", thrown.getMessage());
    }

    /**
     * Simulando Bean
     */
    //@Test
    void debeCrearUnaRecarga() {
        when(personaPersistenceAdapter.listarByid(20)).thenReturn(Optional.of(Persona.getInstance(
          20,
          "Juan",
          "juan@gmail.com"
        )));

        //Recarga recarga = Recarga.getInstance(6000, "3006087877", "TIGO", 20);

//        when(recargaPersistenceAdapter.save(recarga)).thenReturn(
//          Recarga.getInstance(20, 6000, "3006087877", "TIGO", 20)
//        );

        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          6000,
          "3006087877",
          "TIGO",
          20
        );

//        recarga = recargaService.crear(recargaSolicitudCrear);
//
//        Assertions.assertEquals("3006087877", recarga.getCelular());
//        Assertions.assertEquals(6000, recarga.getValor());
    }

}


