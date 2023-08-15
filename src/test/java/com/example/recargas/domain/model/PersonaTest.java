package com.example.recargas.domain.model;

import com.example.recargas.domain.dto.PersonaSolicitudActualizar;
import com.example.recargas.domain.exception.CampoConException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonaTest {

    @Test
    void debeCrearInstanciaNombreEmail() {

        Persona persona = Persona.getInstance("Juan", "juan@gmail.com");

        assertEquals("Juan", persona.getNombre());
        assertEquals("juan@gmail.com", persona.getEmail());

    }

    @Test
    void debeCrearInstanciaIdNombreEmail() {

        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        assertEquals(1L, persona.getId());
        assertEquals("Juan", persona.getNombre());
        assertEquals("juan@gmail.com", persona.getEmail());

    }

    @Test
    void noDebeCrearInstanciaNombreNull() {

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Persona persona = Persona.getInstance(null, "juan@gmail.com");
        });

        Assertions.assertEquals("El campo nombre es obligatorio", thrown.getMessage());

    }

    @Test
    void noDebeCrearInstanciaEmailNull() {

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Persona persona = Persona.getInstance("Juan", null);
        });

        Assertions.assertEquals("El campo email es obligatorio", thrown.getMessage());

    }
}
