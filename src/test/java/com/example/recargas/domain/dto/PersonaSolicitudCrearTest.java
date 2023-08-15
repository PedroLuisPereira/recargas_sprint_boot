package com.example.recargas.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonaSolicitudCrearTest {

    @Test
    void debeCrearInstancia() {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Juan", "juan@gmail.com");

        assertEquals("Juan", personaSolicitudCrear.getNombre());
        assertEquals("juan@gmail.com", personaSolicitudCrear.getEmail());

    }

    @Test
    void debeCrearInstanciaGet() {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear("Juan", "juan@gmail.com");
        personaSolicitudCrear.setNombre("Juan");
        personaSolicitudCrear.setEmail("juan@gmail.com");

        assertEquals("Juan", personaSolicitudCrear.getNombre());
        assertEquals("juan@gmail.com", personaSolicitudCrear.getEmail());

    }

    @Test
    void debeCrearInstanciaToString() {

        PersonaSolicitudCrear personaSolicitudCrear = new PersonaSolicitudCrear();

        personaSolicitudCrear.setNombre("Juan");
        personaSolicitudCrear.setEmail("juan@gmail.com");

        String valor = personaSolicitudCrear.toString();

        assertNotNull(valor);
    }



}
