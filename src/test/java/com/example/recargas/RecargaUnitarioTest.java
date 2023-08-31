package com.example.recargas;

import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.exception.CampoConException;
import com.example.recargas.domain.exception.RegistroDuplicadoException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.service.PersonaService;
import com.example.recargas.domain.service.RecargaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties") //bd de test
@Sql("/test-mysql.sql") //consultas a ejecutar
class RecargaUnitarioTest {
    @Autowired
    RecargaService recargaService;

    @Test
    void debeCrearUnNuevoRegistro() {

        RecargaSolicitudCrear recargaSolicitudCrear = new RecargaSolicitudCrear(
          6000,
          "3006087877",
          "TIGO",
          1
        );

        Recarga recarga = recargaService.crear(recargaSolicitudCrear);

        Assertions.assertEquals(6000, recarga.getValor());
        Assertions.assertEquals("3006087877", recarga.getCelular() );

    }



    /*
    @Test
    void debeLanzarErrorAlUnRegistroNombre() {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear(null, "ana@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Persona personas = personaService.crear(personaSolicitudCrear);
        });

        Assertions.assertEquals("El campo nombre es obligatorio", thrown.getMessage());

    }

    @Test
    void debeLanzarErrorAlUnRegistroEmail() {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Mayo", null);

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Persona personas = personaService.crear(personaSolicitudCrear);
        });

        Assertions.assertEquals("El campo email es obligatorio", thrown.getMessage());

    }

    @Test
    void debeLanzarErrorAlUnRegistroEmailYaExiste() {
        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("maria", "ana@gmail.com");
        personaService.crear(personaSolicitudCrear);

        RegistroDuplicadoException thrown = Assertions.assertThrows(RegistroDuplicadoException.class, () -> {
            Persona personas = personaService.crear(personaSolicitudCrear);
        });

        Assertions.assertEquals("Ya existe una persona con el email: ana@gmail.com", thrown.getMessage());

    }

    @Test
    void debeEliminarUnRegistro() {
        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("maria", "ana1@gmail.com");
        Persona persona = personaService.crear(personaSolicitudCrear);

        personaService.eliminar(persona.getId());

        RegistroNotFoundException thrown = Assertions.assertThrows(RegistroNotFoundException.class, () -> {
            Persona personas = personaService.listarById(persona.getId());
        });

        Assertions.assertEquals("No se encontro persona con Id: " + persona.getId() , thrown.getMessage());
    }
*/
}
