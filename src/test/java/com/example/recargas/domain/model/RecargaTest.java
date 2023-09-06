package com.example.recargas.domain.model;

import com.example.recargas.domain.exception.CampoConException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecargaTest {

    @Test
    void debeCrearRecarga() {

        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        Recarga recarga = Recarga.getInstance(
          2000,
          "3105220305",
          "CLARO",
          persona
        );

        assertEquals(2000, recarga.getValor());
        assertEquals("3105220305", recarga.getCelular());
        assertEquals("CLARO", recarga.getOperador());
        assertEquals("Juan", recarga.getPersona().getNombre());

    }

    @Test
    void debeCrearRecargaConId() {

        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        Recarga recarga = Recarga.getInstance(
          1L,
          2000,
          "3105220305",
          "CLARO",
          persona
        );

        assertEquals(1L, recarga.getId());
        assertEquals(2000, recarga.getValor());
        assertEquals("3105220305", recarga.getCelular());
        assertEquals("CLARO", recarga.getOperador());
        assertEquals("Juan", recarga.getPersona().getNombre());

    }

    @Test
    void noDebeCrearRecargaValorCero() {
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              0,
              "3105220305",
              "CLARO",
              persona
            );
        });

        Assertions.assertEquals("El campo valor es obligatorio", thrown.getMessage());
    }

    @Test
    void noDebeCrearRecargaCelularNull() {
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              2000,
              null,
              "CLARO",
              persona
            );
        });

        Assertions.assertEquals("El campo celular es obligatorio", thrown.getMessage());
    }

    @Test
    void noDebeCrearRecargaCelularError() {
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              2000,
              "566",
              "CLARO",
              persona
            );
        });

        Assertions.assertEquals("Celular incorrecto", thrown.getMessage());
    }

    @Test
    void noDebeCrearRecargaOperadorNull() {
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              2000,
              "3105220305",
              null,
              persona
            );
        });

        Assertions.assertEquals("El campo operador es obligatorio", thrown.getMessage());
    }

    @Test
    void noDebeCrearRecargaOperadorError() {
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              2000,
              "3105220305",
              "XXX",
              persona
            );
        });

        Assertions.assertEquals("Operador incorrecto", thrown.getMessage());
    }

    @Test
    void noDebeCrearRecargaOperadorCelular() {
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              2000,
              "3105220305",
              "TIGO",
              persona
            );
        });

        Assertions.assertEquals("Celular no corresponde al operador", thrown.getMessage());
    }

    @Test
    void noDebeCrearRecargaOperadorCelular2() {
        Persona persona = Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              2000,
              "3005220305",
              "MOVISTAR",
              persona
            );
        });

        Assertions.assertEquals("Celular no corresponde al operador", thrown.getMessage());
    }

    @Test
    void noDebeCrearRecargaPersonaNull() {
        Persona.getInstance(1L, "Juan", "juan@gmail.com");

        CampoConException thrown = Assertions.assertThrows(CampoConException.class, () -> {
            Recarga.getInstance(
              2000,
              "3105220305",
              "CLARO",
              null
            );
        });

        Assertions.assertEquals("El campo persona es obligatorio", thrown.getMessage());
    }

}
