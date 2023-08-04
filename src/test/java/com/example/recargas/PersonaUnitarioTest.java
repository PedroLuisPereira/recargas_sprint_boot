package com.example.recargas;

import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.exception.CampoConException;
import com.example.recargas.domain.exception.RegistroDuplicadoException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.service.PersonaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
class PersonaUnitarioTest {
    @Autowired
    PersonaService personaService;

    @Test
    void debeListarTodosLosRegistros() {
        List<Persona> personas = personaService.listar();
        assertEquals(3, personas.size());
    }

    @Test
    void debeListarUnRegistro() {
        Persona personas = personaService.listarById(1);
        assertEquals("juan@gmail.com", personas.getEmail());
    }

    @Test
    void debeLanzarErrorAlListarUnRegistro() {

        RegistroNotFoundException thrown = Assertions.assertThrows(RegistroNotFoundException.class, () -> {
            Persona personas = personaService.listarById(100);
        });

        Assertions.assertEquals("No se encontro persona con Id: 100", thrown.getMessage());

    }

    @Test
    void debeCrearUnNuevoRegistro() {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Ana", "ana@gmail.com");

        Persona persona = personaService.crear(personaSolicitudCrear);

        Assertions.assertEquals("Ana", persona.getNombre() );
        Assertions.assertEquals("ana@gmail.com", persona.getEmail() );

    }

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

}
