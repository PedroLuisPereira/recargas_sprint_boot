package com.example.recargas.domain.dto;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonaSolicitudActualizarTest {

    @Test
    void debeCrearInstancia() {

        PersonaSolicitudActualizar personaSolicitudActualizar = new PersonaSolicitudActualizar(1L, "Juan", "juan@gmail.com");

        assertEquals(1L, personaSolicitudActualizar.getId());
        assertEquals("Juan", personaSolicitudActualizar.getNombre());
        assertEquals("juan@gmail.com", personaSolicitudActualizar.getEmail());

    }

    @Test
    void debeCrearInstanciaGet() {

        PersonaSolicitudActualizar personaSolicitudActualizar = new PersonaSolicitudActualizar();
        personaSolicitudActualizar.setId(1L);
        personaSolicitudActualizar.setNombre("Juan");
        personaSolicitudActualizar.setEmail("juan@gmail.com");

        assertEquals(1L, personaSolicitudActualizar.getId());
        assertEquals("Juan", personaSolicitudActualizar.getNombre());
        assertEquals("juan@gmail.com", personaSolicitudActualizar.getEmail());

    }

    @Test
    void debeCrearInstanciaToString() {

        PersonaSolicitudActualizar personaSolicitudActualizar = new PersonaSolicitudActualizar();
        personaSolicitudActualizar.setId(1L);
        personaSolicitudActualizar.setNombre("Juan");
        personaSolicitudActualizar.setEmail("juan@gmail.com");

        String valor = personaSolicitudActualizar.toString();

        assertNotNull(valor);
    }



}
